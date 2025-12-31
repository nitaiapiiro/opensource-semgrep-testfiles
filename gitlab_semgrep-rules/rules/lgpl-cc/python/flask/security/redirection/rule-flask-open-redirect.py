# License: Commons Clause License Condition v1.0[LGPL-2.1-only]
# source: https://github.com/semgrep/semgrep-rules/blob/release/python/flask/security/open-redirect.py
from urlparse import urlparse

from flask import request, redirect
from flask import Flask, redirect, request, url_for
from werkzeug.urls import url_parse

app = Flask(__name__)


@app.route("open_redirect/")
def open_redirect():
    # ruleid: python_flask_rule-flask-open-redirect
    url = request.args.get("url")
    print("something")
    return redirect(url)


@app.route("not_open_redirect/")
def not_open_redirect():
    page = request.args.get("page")
    if page == "about":
        # ok: python_flask_rule-flask-open-redirect
        url = "/about/"
        return redirect(url)
    elif page == "test":
        # ok: python_flask_rule-flask-open-redirect
        redirect(f"{request.path}/")
    else:
        # ok: python_flask_rule-flask-open-redirect
        redirect(request.path + "?failed")


@app.route("filter")
def filter():
    # ok: python_flask_rule-flask-open-redirect
    next_page = request.args.get('next')
    if not next_page or url_parse(next_page).netloc != '':
        next_page = url_for('main.index')
    return redirect(next_page)


# cf. https://github.com/mideind/Netskrafl/blob/2e1933ad0710a4425c319fde3b92b2a70729ed80/netskrafl.py#L1712


@app.route("/userprefs", methods=["GET", "POST"])
@auth_required()
def userprefs():
    """ Handler for the user preferences page """

    user = current_user()

    uf = UserForm()
    err = dict()

    # The URL to go back to, if not main.html
    # ruleid: python_flask_rule-flask-open-redirect
    from_url = request.args.get("from", None)

    if request.method == "GET":
        # Entering the form for the first time: load the user data
        uf.init_from_user(user)
    elif request.method == "POST":
        # Attempting to submit modified data: retrieve it and validate
        uf.init_from_form(request.form)
        err = uf.validate()
        if not err:
            # All is fine: store the data back in the user entity
            uf.store(user)
            return redirect(from_url or url_for("main"))

    # Render the form with the current data and error messages, if any
    return render_template("userprefs.html", uf=uf, err=err, from_url=from_url)


# Positive Test Cases (Vulnerable - should trigger the rule)
@app.route("/open_redirect_brackets_positive/")
def open_redirect_brackets_positive():
    # ruleid: python_flask_rule-flask-open-redirect
    url = request.args["redirect_url"]
    return redirect(url)


@app.route("/open_redirect_function_call_positive/")
def open_redirect_function_call_negative():
    # ok: python_flask_rule-flask-open-redirect
    url = validate_url(request.args["redirect_url"])
    if url:
        return redirect(url)
    else:
        return url_for("home")


@app.route("/open_redirect_direct_assignment_positive/")
def open_redirect_direct_assignment_positive():
    # ruleid: python_flask_rule-flask-open-redirect
    url = request.form["destination"]
    return redirect(url)


# Negative Test Cases (Safe - should not trigger the rule)
@app.route("/safe_redirect_brackets_negative/")
def safe_redirect_brackets_negative():
    # ok: python_flask_rule-flask-open-redirect
    url = validate_url(request.args.get("redirect_url", ""))
    if url:
        return redirect(url)
    else:
        return url_for("home")


@app.route("/safe_redirect_function_call_negative/")
def safe_redirect_function_call_negative():
    # ok: python_flask_rule-flask-open-redirect
    url = url_for("some_endpoint", param1="value1")
    return redirect(url)


@app.route("/safe_redirect_direct_assignment_negative/")
def safe_redirect_direct_assignment_negative():
    # ok: python_flask_rule-flask-open-redirect
    return redirect("/home")


@app.route("/open_redirect_positive/", methods=["POST"])
def open_redirect_positive():
    # ruleid: python_flask_rule-flask-open-redirect
    return redirect(request.get_data().decode('utf-8'))


@app.route("/safe_redirect_positive/", methods=["POST"])
def safe_redirect_positive():
    # ok: python_flask_rule-flask-open-redirect
    url = request.get_data().decode('utf-8')
    if validate_url(url):  # Validate before redirect
        return redirect(url)
    else:
        return "Invalid URL"


# Placeholder Function for Validation
def validate_url(url):
    parsed_url = urlparse(url)
    if parsed_url.scheme not in ('http', 'https') or parsed_url.netloc != '':
        return None
    return url
