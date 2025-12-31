# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

from django.http import HttpResponse
from urllib.parse import parse_qs, urlparse
from myapp.models import User, Person
from django.shortcuts import render
from django.views import View



import logging
logger = logging.getLogger(__name__)

def index(request):
    return render(request, 'index.html')

def debug(input):
    logger.debug('[DEBUG] %s' % input)
    return input

def test_sql_injection1(request):

    # ruleid: python_django_rule-django-raw-used
    res = User.objects.raw(debug("SELECT * FROM myapp_user WHERE username = '{}'".format(request.GET.get('username', 'testuser2'))))
    try:
        user = next(iter(res))  
        return HttpResponse(f"Test Case {request.resolver_match.view_name}: Found user!<br>Username: {user.username}<br>Email: {user.email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        
        return HttpResponse(f"An error occurred: {str(e)}")
    
# request object inline with query
# request object format - request.$W.get(...)
# string interpolation with % operator 
def test_sql_injection2(request):

    # ruleid: python_django_rule-django-raw-used
    res = User.objects.raw("SELECT * FROM myapp_user WHERE username = '%s'" % request.GET.get('username', 'testuser2'))
    try:
        user = next(iter(res))  
        return HttpResponse(f"Test Case {request.resolver_match.view_name}: Found user!<br>Username: {user.username}<br>Email: {user.email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        
        return HttpResponse(f"An error occurred: {str(e)}")
   
  
# request object inline with query
# request object format - request.$W.get(...)
# string interpolation with F-Strings
def test_sql_injection3(request):

    # ruleid: python_django_rule-django-raw-used
    res = User.objects.raw(f'SELECT * FROM myapp_user WHERE username = "{request.GET.get("username", "testuser2")}"')
    try:
        user = next(iter(res))  
        return HttpResponse(f"Test Case {request.resolver_match.view_name}: Found user!<br>Username: {user.username}<br>Email: {user.email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        
        return HttpResponse(f"An error occurred: {str(e)}")


# request object inline with query
# request object format - request.$W.get(...)
# string concatenation with + operator
def test_sql_injection4(request):

    # ruleid: python_django_rule-django-raw-used
    res = User.objects.raw("SELECT * FROM myapp_user WHERE username = '" + request.GET.get('username', 'testuser2') + "'")
    try:
        user = next(iter(res))  
        return HttpResponse(f"Test Case {request.resolver_match.view_name}: Found user!<br>Username: {user.username}<br>Email: {user.email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        
        return HttpResponse(f"An error occurred: {str(e)}")

#-------------------------------------------------------------

# request object not inline
# string interpolation with str.format() method
def test_sql_injection5(request):

    uname = request.GET.get('username', 'testuser2')
    query = "SELECT * FROM myapp_user WHERE username = '{}'".format(uname)
    # ruleid: python_django_rule-django-raw-used
    res = User.objects.raw(query)
    try:
        user = next(iter(res))  
        return HttpResponse(f"Test Case {request.resolver_match.view_name}: Found user!<br>Username: {user.username}<br>Email: {user.email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        
        return HttpResponse(f"An error occurred: {str(e)}")
    
# request object not inline
# string interpolation with % operator 
def test_sql_injection6(request):

    uname = request.GET.get('username', 'testuser2')
    query = "SELECT * FROM myapp_user WHERE username = '%s'" % uname
    # ruleid: python_django_rule-django-raw-used
    res = User.objects.raw(query)
    try:
        user = next(iter(res))  
        return HttpResponse(f"Test Case {request.resolver_match.view_name}: Found user!<br>Username: {user.username}<br>Email: {user.email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        
        return HttpResponse(f"An error occurred: {str(e)}")
   
  
# request object not inline
# string interpolation with F-Strings
def test_sql_injection7(request):

    uname = request.GET.get('username', 'testuser2')
    query = f'SELECT * FROM myapp_user WHERE username = "{uname}"'
    # ruleid: python_django_rule-django-raw-used
    res = User.objects.raw(query)
    try:
        user = next(iter(res))  
        return HttpResponse(f"Test Case {request.resolver_match.view_name}: Found user!<br>Username: {user.username}<br>Email: {user.email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        
        return HttpResponse(f"An error occurred: {str(e)}")


# request object not inline
# string concatenation with + operator
def test_sql_injection8(request):

    uname = request.GET.get('username', 'testuser2')
    query = "SELECT * FROM myapp_user WHERE username = '" + uname + "'"
    # ruleid: python_django_rule-django-raw-used
    res = User.objects.raw(query)
    try:
        user = next(iter(res))  
        return HttpResponse(f"Test Case {request.resolver_match.view_name}: Found user!<br>Username: {user.username}<br>Email: {user.email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        
        return HttpResponse(f"An error occurred: {str(e)}")
    
#-------------------------------------------------
        

# request object format - request.$W(...)
# string interpolation with str.format() method
def test_sql_injection9(request):

    query_string = urlparse(request.get_full_path()).query
    params = parse_qs(query_string)
    uname = params.get('username', [''])[0]
    # ruleid: python_django_rule-django-raw-used
    res = User.objects.raw("SELECT * FROM myapp_user WHERE username = '{}'".format(uname))
    try:
        user = next(iter(res))  
        return HttpResponse(f"Test Case {request.resolver_match.view_name}: Found user!<br>Username: {user.username}<br>Email: {user.email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        
        return HttpResponse(f"An error occurred: {str(e)}")
    
# request object format - request.$W(...)
# string interpolation with % operator 
def test_sql_injection10(request):

    query_string = urlparse(request.get_full_path()).query
    params = parse_qs(query_string)
    uname = params.get('username', [''])[0]
    # ruleid: python_django_rule-django-raw-used
    res = User.objects.raw("SELECT * FROM myapp_user WHERE username = '%s'" % uname)
    try:
        user = next(iter(res))  
        return HttpResponse(f"Test Case {request.resolver_match.view_name}: Found user!<br>Username: {user.username}<br>Email: {user.email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        
        return HttpResponse(f"An error occurred: {str(e)}")
   
  
# request object format - request.$W(...)
# string interpolation with F-Strings
def test_sql_injection11(request):

    query_string = urlparse(request.get_full_path()).query
    params = parse_qs(query_string)
    uname = params.get('username', [''])[0]
    # ruleid: python_django_rule-django-raw-used
    res = User.objects.raw(f'SELECT * FROM myapp_user WHERE username = "{uname}"')
    try:
        user = next(iter(res))  
        return HttpResponse(f"Test Case {request.resolver_match.view_name}: Found user!<br>Username: {user.username}<br>Email: {user.email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        
        return HttpResponse(f"An error occurred: {str(e)}")


# request object format - request.$W(...)
# string concatenation with + operator
def test_sql_injection12(request):

    query_string = urlparse(request.get_full_path()).query
    params = parse_qs(query_string)
    uname = params.get('username', [''])[0]
    # ruleid: python_django_rule-django-raw-used
    res = User.objects.raw("SELECT * FROM myapp_user WHERE username = '" + uname + "'")
    try:
        user = next(iter(res))  
        return HttpResponse(f"Test Case {request.resolver_match.view_name}: Found user!<br>Username: {user.username}<br>Email: {user.email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        
        return HttpResponse(f"An error occurred: {str(e)}")  

     
#-------------------------------------------------
    
# request object format - request.$W[...]
# string interpolation with str.format() method
def test_sql_injection13(request):

    uname = request.GET["username"] 
    # ruleid: python_django_rule-django-raw-used
    res = User.objects.raw("SELECT * FROM myapp_user WHERE username = '{}'".format(uname))
    try:
        user = next(iter(res))  
        return HttpResponse(f"Test Case {request.resolver_match.view_name}: Found user!<br>Username: {user.username}<br>Email: {user.email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        
        return HttpResponse(f"An error occurred: {str(e)}")
    
# request object format - request.$W[...]
# string interpolation with % operator 
def test_sql_injection14(request):

    uname = request.GET["username"] 
    # ruleid: python_django_rule-django-raw-used
    res = User.objects.raw("SELECT * FROM myapp_user WHERE username = '%s'" % uname)
    try:
        user = next(iter(res))  
        return HttpResponse(f"Test Case {request.resolver_match.view_name}: Found user!<br>Username: {user.username}<br>Email: {user.email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        
        return HttpResponse(f"An error occurred: {str(e)}")
   
  
# request object format - request.$W[...]
# string interpolation with F-Strings
def test_sql_injection15(request):

    uname = request.GET["username"] 
    # ruleid: python_django_rule-django-raw-used
    res = User.objects.raw(f'SELECT * FROM myapp_user WHERE username = "{uname}"')
    try:
        user = next(iter(res))  
        return HttpResponse(f"Test Case {request.resolver_match.view_name}: Found user!<br>Username: {user.username}<br>Email: {user.email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        return HttpResponse(f"An error occurred: {str(e)}")


# request object format - request.$W[...]
# string concatenation with + operator
def test_sql_injection16(request):

    uname = request.GET["username"] 
    # ruleid: python_django_rule-django-raw-used
    res = User.objects.raw("SELECT * FROM myapp_user WHERE username = '" + uname + "'")
    try:
        user = next(iter(res))  
        return HttpResponse(f"Test Case {request.resolver_match.view_name}: Found user!<br>Username: {user.username}<br>Email: {user.email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        return HttpResponse(f"An error occurred: {str(e)}")  

#----------------------

# Using Parameterized query - Safe
# True negative case
def test_sql_injection17(request):

    uname = request.GET["username"] 
    # ok: python_django_rule-django-raw-used
    res = User.objects.raw('SELECT * FROM myapp_user WHERE username = %s', (uname,))
    try:
        user = next(iter(res))  
        return HttpResponse(f"Test Case {request.resolver_match.view_name} (Safe): Found user!<br>Username: {user.username}<br>Email: {user.email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        return HttpResponse(f"An error occurred: {str(e)}") 


# Using translations map
# Not using Parameterized queries
def test_sql_injection18(request):

    name_map = {'username': 'person_name', 'email':'person_email'}
    uname = request.GET["username"] 
    # ruleid: python_django_rule-django-raw-used
    res = Person.objects.raw("SELECT * FROM myapp_user WHERE username = '%s'" % uname, translations=name_map)
    try:
        person = next(iter(res))  
        return HttpResponse(f"Test Case {request.resolver_match.view_name}: Found user!<br>Username: {person.person_name}<br>Email: {person.person_email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        return HttpResponse(f"An error occurred: {str(e)}") 



# Using translations map
# No user input
# True negative
def test_sql_injection19(request):

    name_map = {'username': 'person_name', 'email':'person_email'}

    # ok: python_django_rule-django-raw-used
    res = Person.objects.raw("SELECT * FROM myapp_user", translations=name_map)
    try:
        person = next(iter(res))  
        return HttpResponse(f"Test Case {request.resolver_match.view_name} (Safe): Found user!<br>Username: {person.person_name}<br>Email: {person.person_email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        return HttpResponse(f"An error occurred: {str(e)}")  
        

# Using translations map
# Using Parameterized queries - Safe
# True negative
def test_sql_injection20(request):

    name_map = {'username': 'person_name', 'email':'person_email'}
    # ok: python_django_rule-django-raw-used
    res = Person.objects.raw("SELECT * FROM myapp_user WHERE username = %s LIMIT 1", [request.GET["username"]], translations=name_map)
    try:
        person = next(iter(res))  
        return HttpResponse(f"Test Case {request.resolver_match.view_name} (Safe): Found user!<br>Username: {person.person_name}<br>Email: {person.person_email}")
    except StopIteration:
        return HttpResponse("No such user found.")


# With empty parameters as tuple
# Not using Parameterized queries
def test_sql_injection21(request):
    uname = request.GET["username"] 
    res = User.objects.raw(
                # ruleid: python_django_rule-django-raw-used
                "SELECT * FROM myapp_user WHERE username = '%s'" % uname,
                params=(),
                )
    try:
        user = next(iter(res))  
        return HttpResponse(f"Test Case {request.resolver_match.view_name} : Found user!<br>Username: {user.username}<br>Email: {user.email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        return HttpResponse(f"An error occurred: {str(e)}") 
    
# With empty parameters as list
# No User input: String interpolation % operator
# Not using Parameterized queries
def test_sql_injection22(request):
    uname = request.GET["username"]     
    # ruleid: python_django_rule-django-raw-used
    res = User.objects.raw("SELECT * FROM myapp_user WHERE username = '%s'" % uname, [])
    try:
        user = next(iter(res))  
        return HttpResponse(f"Test Case {request.resolver_match.view_name}: Found user!<br>Username: {user.username}<br>Email: {user.email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        return HttpResponse(f"An error occurred: {str(e)}") 

# With empty parameters as list
# No User input
# True Negative
def test_sql_injection23(request):    
    # ok: python_django_rule-django-raw-used
    res = User.objects.raw("SELECT * FROM myapp_user WHERE username = '%s'" % "testuser2", [])
    try:
        user = next(iter(res))  
        return HttpResponse(f"Test Case {request.resolver_match.view_name} (Safe): Found user!<br>Username: {user.username}<br>Email: {user.email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        return HttpResponse(f"An error occurred: {str(e)}") 

#----------------------------------------------------

# No User input
# String interpolation % operator 
# True negative case
def test_sql_injection24(request):

    val = "testuser1" 
    # ok: python_django_rule-django-raw-used
    res = User.objects.raw("SELECT * FROM myapp_user WHERE username = '%s'" % val)
    try:
        user = next(iter(res))  
        return HttpResponse(f"Test Case {request.resolver_match.view_name} (Safe): Found user!<br>Username: {user.username}<br>Email: {user.email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        return HttpResponse(f"An error occurred: {str(e)}") 

# No User input
# String concatenation + operator 
# True negative case
def test_sql_injection25(request):
    val = "testuser2"
    query = "SELECT * FROM myapp_user WHERE username = '" + val +"'"     
    # ok: python_django_rule-django-raw-used
    res = User.objects.raw(query)
    try:
        user = next(iter(res))  
        return HttpResponse(f"Test Case {request.resolver_match.view_name} (Safe): Found user!<br>Username: {user.username}<br>Email: {user.email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        return HttpResponse(f"An error occurred: {str(e)}") 

# No User input
# string interpolation with F-Strings
# True negative case
def test_sql_injection26(request):
    val = "testuser2"
    query = f"SELECT * FROM myapp_user WHERE username = '{val}'"    
    # ok: python_django_rule-django-raw-used
    res = User.objects.raw(query)
    try:
        user = next(iter(res))  
        return HttpResponse(f"Test Case {request.resolver_match.view_name} (Safe): Found user!<br>Username: {user.username}<br>Email: {user.email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        return HttpResponse(f"An error occurred: {str(e)}") 

# No User input
# string interpolation with str.format() method
# True negative case
def test_sql_injection27(request):
    val = "testuser2"
    query = "SELECT * FROM myapp_user WHERE username = '{}'".format(val)     
    # ok: python_django_rule-django-raw-used
    res = User.objects.raw(query)
    try:
        user = next(iter(res))  
        return HttpResponse(f"Test Case {request.resolver_match.view_name} (Safe): Found user!<br>Username: {user.username}<br>Email: {user.email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        return HttpResponse(f"An error occurred: {str(e)}") 
    
#----------

# Testing different parameter name
def test_sql_injection27(myrequest):
    uname = myrequest.GET["username"]
    query = "SELECT * FROM myapp_user WHERE username = '{}'".format(uname)     
    # ruleid: python_django_rule-django-raw-used
    res = User.objects.raw(query)
    try:
        user = next(iter(res))  
        return HttpResponse(f"Test Case {myrequest.resolver_match.view_name} : Found user!<br>Username: {user.username}<br>Email: {user.email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        return HttpResponse(f"An error occurred: {str(e)}") 
    
# Testing multiple view arguments
def test_sql_injection28(request, uname):
    query = "SELECT * FROM myapp_user WHERE username = '{}'".format(uname)     
    # ruleid: python_django_rule-django-raw-used
    res = User.objects.raw(query)
    try:
        user = next(iter(res))  
        return HttpResponse(f"Test Case {request.resolver_match.view_name} : Found user!<br>Username: {user.username}<br>Email: {user.email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        return HttpResponse(f"An error occurred: {str(e)}") 

# Testing inter-procedural tainted query returned
def intra_file_query_builder(myrequest=None):
    if myrequest is None:
        uname = myrequest.GET["username"]
        query = "SELECT * FROM myapp_user WHERE username = '{}'".format(uname)
    return query

# Testing intra-file inter-procedural vulnerable calls
def test_sql_injection29(myrequest):
    # ruleid: python_django_rule-django-raw-used
    res = User.objects.raw(intra_file_query_builder(myrequest))
    try:
        user = next(iter(res))  
        return HttpResponse(f"Test Case {myrequest.resolver_match.view_name} : Found user!<br>Username: {user.username}<br>Email: {user.email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        return HttpResponse(f"An error occurred: {str(e)}") 

# Testing vulnerable calls in class-based views 
class TestSQLInjection30(View):

    # Testing inter-procedural tainted query returned 
    def intra_class_query_builder(self):
        uname = self.request.GET["username"]
        query = "SELECT * FROM myapp_user WHERE username = '{}'".format(uname)     
        return query

    # Testing intra-class and inter-procedural vulnerable calls
    def get(self, myrequest):
        # todoruleid: python_django_rule-django-raw-used
        res = User.objects.raw(self.intra_class_query_builder())
        try:
            user = next(iter(res))  
            return HttpResponse(f"Test Case {myrequest.resolver_match.view_name} : Found user!<br>Username: {user.username}<br>Email: {user.email}")
        except StopIteration:
            return HttpResponse("No such user found.")
        except Exception as e:
            return HttpResponse(f"An error occurred: {str(e)}")     

from .helpers import inter_file_query_builder

# Testing inter-file inter-procedural vulnerable calls with parameters
def test_sql_injection31(myrequest):
    # ruleid: python_django_rule-django-raw-used
    res = User.objects.raw(inter_file_query_builder(myrequest))
    try:
        user = next(iter(res))  
        return HttpResponse(f"Test Case {myrequest.resolver_match.view_name} : Found user!<br>Username: {user.username}<br>Email: {user.email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        return HttpResponse(f"An error occurred: {str(e)}") 

# Testing inter-file inter-procedural vulnerable calls without parameters
def test_sql_injection32(myrequest):
    # todoruleid: python_django_rule-django-raw-used
    res = User.objects.raw(inter_file_query_builder())
    try:
        user = next(iter(res))  
        return HttpResponse(f"Test Case {myrequest.resolver_match.view_name} : Found user!<br>Username: {user.username}<br>Email: {user.email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        return HttpResponse(f"An error occurred: {str(e)}") 

# Testing safe inter-procedural and inter-file
def intra_file_safe_function(param=None):
    retval = "SELECT * FROM myapp_user WHERE username = 'testuser1'"
    return retval

# Testing safe inter-procedural and intra-file
def test_sql_injection33(myrequest):
    # ok: python_django_rule-django-raw-used
    res = User.objects.raw(intra_file_safe_function())
    try:
        user = next(iter(res))  
        return HttpResponse(f"Test Case {myrequest.resolver_match.view_name} : Found user!<br>Username: {user.username}<br>Email: {user.email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        return HttpResponse(f"An error occurred: {str(e)}") 

from .helpers import inter_file_safe_function

# Testing safe inter-procedural and inter-file
def test_sql_injection34(myrequest):
    # ok: python_django_rule-django-raw-used
    res = User.objects.raw(inter_file_safe_function())
    try:
        user = next(iter(res))  
        return HttpResponse(f"Test Case {myrequest.resolver_match.view_name} : Found user!<br>Username: {user.username}<br>Email: {user.email}")
    except StopIteration:
        return HttpResponse("No such user found.")
    except Exception as e:
        return HttpResponse(f"An error occurred: {str(e)}") 
