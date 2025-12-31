import os
import logging

from flask import Flask, render_template, request, url_for, redirect

from flask_sqlalchemy import SQLAlchemy
from sqlalchemy.sql import func, text, select
import sqlite3


basedir = os.path.abspath(os.path.dirname(__file__))

app = Flask(__name__)

app.logger.setLevel(logging.DEBUG)
app.logger.addHandler(logging.StreamHandler())

app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///' + os.path.join(basedir,'db.sqlite3')
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

db = SQLAlchemy(app)

app.app_context().push()

class User(db.Model):

    id = db.Column(
                    db.Integer,
                    primary_key=True
                )
    
    firstname = db.Column(
                            db.String(100),
                            nullable=False
                        )
    
    lastname = db.Column(
                            db.String(100),
                            nullable=False
                        )
    
    email = db.Column(
                        db.String(80), 
                        unique=True, 
                        nullable=False
                    )
    
    age = db.Column(
                    db.Integer
                    )
    
    created_at = db.Column(
                            db.DateTime(timezone=True),
                           server_default=func.now()
                           )
    
    def __repr__(self):
        return f'<user {self.firstname}>'

# for reference in case the DB is populated programmatically only
# db.drop_all()
# db.create_all()

try:
    ormuser = User( firstname='ORM', lastname='user',
                    email='ormuser@example.com', age=21,
                    )
    db.session.add(ormuser)
    db.session.commit()
except Exception as e:
    app.logger.debug(f"[!] {e}")


def testcases_links():
    links = {'vulnerable':[],'safe':[]} 
    for rule in app.url_map.iter_rules():

        category = None

        if 'vulnerable' in rule.endpoint:
            category = 'vulnerable'
        elif 'safe' in rule.endpoint:
            category = 'safe'

        if category:
            url = url_for(rule.endpoint, **(rule.defaults or {}))
            links[category].append((url, rule.endpoint))
    return links

@app.route('/')
def index():
    result=[]
    result = User.query.all()
    return render_template('index.html', users=result, testcases=testcases_links())

@app.route('/vulnerable01', methods=['GET', 'POST'])
def vulnerable01():
    result=[]
    #ok: python_flask_rule-tainted-sql-string
    query_prefix = "SELECT * FROM user"
    #ruleid: python_flask_rule-tainted-sql-string
    result = db.session.execute(text(f" {query_prefix} WHERE firstname = '{request.args.get('param','')}'"))
    return render_template('result.html', users=result)

@app.route('/safe01', methods=('GET', 'POST'))
def safe01():
    result=[]
    #ok: python_flask_rule-tainted-sql-string
    result= db.session.execute(text(f'SELECT * FROM user WHERE age == 22'))
    return render_template('result.html', users=result)

@app.route('/safe02', methods=['GET', 'POST'])
def safe02():
    result=[]
    #ok: python_flask_rule-tainted-sql-string
    query = text('SELECT * FROM user WHERE firstname = :myparam')
    name = "john"
    #ok: python_flask_rule-tainted-sql-string
    result = db.session.execute(query, {"myparam":name})
    return render_template('result.html', users=result)

def vulnerable03_helper():
    if 'param' in request.args:
        app.logger.debug("[!] name GET parameter provided")
    return request.args.get('param') 

@app.route('/vulnerable03', methods=['GET'])
def vulnerable03():
    result = []
    #todoruleid: python_flask_rule-tainted-sql-string
    result = db.session.execute(text("SELECT * FROM user WHERE firstname = '%s'" % vulnerable03_helper()))
    return render_template('result.html', users=result)

def vulnerable04_helper(args):
    if 'param' in args:
        return args.get('param')

@app.route('/vulnerable04', methods=['GET'])
def vulnerable04():
    result = []
    #ruleid: python_flask_rule-tainted-sql-string
    result = db.session.execute(text("SELECT * FROM user WHERE firstname = '%s'" % vulnerable04_helper(request.args)))
    return render_template('result.html', users=result)

@app.route('/safe05', methods=('GET','POST'))
def safe05():
    result = []
    if request.method == 'GET':
        name = request.args.get('param','')
    elif request.method == 'POST':
        name = request.form.get('param','')

    #ok: python_flask_rule-tainted-sql-string
    query = text('SELECT * FROM user WHERE firstname = :myparam')

    #ok: python_flask_rule-tainted-sql-string
    result = db.session.execute(query, {"myparam":name})

    return render_template('result.html', users=result)

def vulnerable06_helper01(query):
    #requires interprocedural capabilities to know for certain that query is an SQL query
    #todoid: python_flask_rule-tainted-sql-string
    result = db.session.execute(query)
    return result

def vulnerable06_helper02(ages):
    result=[]
    ages=list(ages)
    if len(ages):
        #requires interprocedural capabilities to know for certain that age comes from a request
        #todoid: python_flask_rule-tainted-sql-string
        result= db.session.execute(text(f'SELECT * FROM user WHERE age >= {ages[0][0]}'))
    return result

@app.route('/vulnerable06', methods=['GET', 'POST'])
def vulnerable06():
    result = []
    if request.method == 'GET':
        name = request.args.get('param')
    elif request.method == 'POST':
        name = request.form.get('param')

    if name:
        #ruleid: python_flask_rule-tainted-sql-string
        result = vulnerable06_helper02(
                    vulnerable06_helper01(
                        text(f'SELECT age FROM user WHERE firstname = \"{name}\"')
                    )
        )
    return render_template('result.html', users=result)

@app.route('/vulnerable07', methods=['GET', 'POST'])
def vulnerable07():
    result=[]
    if request.method == 'GET':
        name = request.args.get('param')
    elif request.method == 'POST':
        name = request.form.get('param')

    if name:
        query_prefix = "SELECT * FROM user"
        #ruleid: python_flask_rule-tainted-sql-string
        result = db.session.execute(text(f'{query_prefix} WHERE firstname = \"{name}\"'))

    return render_template('result.html', users=result)

@app.route('/safe06', methods=['GET', 'POST'])
def safe06():
    result=[]
    #ok: python_flask_rule-tainted-sql-string
    query_prefix = "SELECT * FROM user"
    #ok: python_flask_rule-tainted-sql-string
    query = query_prefix+";"
    #ok: python_flask_rule-tainted-sql-string
    result = db.session.execute(text(query))
    return render_template('result.html', users=result)

@app.route('/safe07', methods=['GET', 'POST'])
def safe07():
    result=[]
    #ok: python_flask_rule-tainted-sql-string
    query_prefix = "SELECT * FROM user"
    #ok: python_flask_rule-tainted-sql-string
    result = db.session.execute(text(f'{query_prefix} WHERE firstname ="john"'))
    return render_template('result.html', users=result)

@app.route('/vulnerable08', methods=['GET'])
def vulnerable08():
    result=[]
    #ruleid: python_flask_rule-tainted-sql-string
    result = db.session.execute(text("SELECT * FROM user WHERE firstname = '%s'" % request.args.get('param')))
    return render_template('result.html', users=result)

@app.route('/safe09', methods=['GET', 'POST'])
def safe09():
    result=[]
    mytable = "user"
    #ok: python_flask_rule-tainted-sql-string
    result = db.session.execute(text("SELECT * FROM " + mytable))
    return render_template('result.html', users=result)

@app.route('/vulnerable09', methods=['GET', 'POST'])
def vulnerable09():
    result=[]
    if request.method == 'GET':
        query = request.args.get('param')
    elif request.method == 'POST':
        query = request.form.get('param')

    if query:
        #ruleid: python_flask_rule-tainted-sql-string
        result = db.session.execute(text('SELECT * FROM user WHERE lastname=\"{}\"'.format(query)))

    return render_template('result.html', users=result)

@app.route('/safe10', methods=['GET', 'POST'])
def safe10():
    result=[]
    #ok: python_flask_rule-tainted-sql-string
    result = db.session.execute(text('SELECT * FROM user'))
    return render_template('result.html', users=result)

@app.get('/vulnerable11')
def vulnerable11():
    result=[]
    #ruleid: python_flask_rule-tainted-sql-string
    result = db.session.execute(text("SELECT * FROM user WHERE firstname = '%s'" % request.args.get('param')))
    return render_template('result.html', users=result)

@app.route('/<int:user_id>/')
def user(user_id):
    result=[]
    result = User.query.get_or_404(user_id)
    return render_template('user.html', user=result)

@app.route('/new/', methods=['POST','GET'])
def new():
    if request.method == 'POST':
        firstname = request.form['firstname']
        lastname = request.form['lastname']
        email = request.form['email']
        age = int(request.form['age'])
        user = User(firstname=firstname,
                    lastname=lastname,
                    email=email,
                    age=age
                    )
        db.session.add(user)
        db.session.commit()
        return redirect(url_for('index'))
    else:
        return render_template('create.html')

@app.route('/<int:user_id>/edit/', methods=['POST'])
def edit(user_id):
    user = User.query.get_or_404(user_id)

    firstname = request.form['firstname']
    lastname = request.form['lastname']
    email = request.form['email']
    age = int(request.form['age'])

    user.firstname = firstname
    user.lastname = lastname
    user.email = email
    user.age = age

    db.session.add(user)
    db.session.commit()

    return redirect(url_for('index'))

@app.post('/<int:user_id>/delete/')
def delete(user_id):
    user = User.query.get_or_404(user_id)
    db.session.delete(user)
    db.session.commit()
    return redirect(url_for('index'))

def sqlite_fetch(query=None, suffix=None):
    connection = sqlite3.connect(os.path.join(basedir,'db.sqlite3'))
    cursor = connection.cursor()
    if query is None:
        query ="""SELECT * FROM main.user 
        WHERE firstname LIKE 'sam'"""
    query = f"{query} {suffix}"
    cursor = cursor.execute( query, () )
    keepgoing = True
    users = []
    while keepgoing:
        try:
            row = cursor.fetchone()
            users.append(
                User(
                    id=row[0],
                    firstname=row[1],
                    lastname=row[2],
                    email=row[3],
                    age=row[4],
                )
            )
            print(row)
        except:
            keepgoing = False
    return users

@app.get('/vulnerable12')
def vulnerable12():
    result=[]
    name = request.args.get('param')
    #ruleid: python_flask_rule-tainted-sql-string
    result=sqlite_fetch(f"SELECT * FROM main.user WHERE firstname LIKE '%{name}%';")
    return render_template('result.html', users=result)

@app.get('/vulnerable13')
def vulnerable13():
    result=[]
    name = request.args.get('param')
    #ruleid: python_flask_rule-tainted-sql-string
    query=f"SELECT * FROM main.user WHERE firstname LIKE '%{name}%';"

    # this rule concerns itself with SQL strings
    # not with SQL driver or ORM specifics 
    #ok: python_flask_rule-tainted-sql-string
    result=sqlite_fetch(query)
    return render_template('result.html', users=result)

@app.get('/vulnerable14')
def vulnerable14():
    result=[]
    name = request.args.get('param')

    #ok: python_flask_rule-tainted-sql-string
    result=f"""SELECT this is not SQL '{name}';"""

    #ok: python_flask_rule-tainted-sql-string
    result=f"""create this is not SQL '{name}';"""

    #todook: python_flask_rule-tainted-sql-string
    result=f"""where did '{name}' go?"""

    return render_template('result.html', users=result)

@app.get('/vulnerable14')
def vulnerable14():
    result=[]
    name = request.args.get('param')
    #ruleid: python_flask_rule-tainted-sql-string
    result=sqlite_fetch(f"""SELECT * FROM main.user 
    WHERE firstname LIKE '%{name}%';""")
    return render_template('result.html', users=result)

@app.get('/vulnerable15')
def vulnerable15():
    result=[]
    orderby = request.args.get('param')
    query ="""SELECT * FROM main.user 
    WHERE firstname LIKE '%s%'"""

    #ruleid: python_flask_rule-tainted-sql-string
    result=sqlite_fetch(query+f"ORDER BY {orderby}")

    #ruleid: python_flask_rule-tainted-sql-string
    result=sqlite_fetch(query, sufix=f"ORDER BY {orderby}")
    
    #ruleid: python_flask_rule-tainted-sql-string
    result=sqlite_fetch(None, sufix=f"ORDER BY {orderby}")

def sqlite_execute(query):

    connection = sqlite3.connect(os.path.join(basedir,'db.sqlite3'))
    cursor = connection.cursor()
    cursor = cursor.execute( query )
    connection.commit()
    cursor.close()

    return True

@app.get('/vulnerable16')
def vulnerable15():
    result=[]
    name =request.args.get('param')
    if name is None:
        name='todo'

    #ruleid: python_flask_rule-tainted-sql-string
    query="INSERT INTO user(firstname, lastname, email, age) VALUES ( '%s','todo','todo@todo.net',99);" % name
    sqlite_execute(query)

    #ruleid: python_flask_rule-tainted-sql-string
    query="DELETE FROM user WHERE age=99);" % name
    sqlite_execute(query)
    
    
    return render_template('result.html', users=result)