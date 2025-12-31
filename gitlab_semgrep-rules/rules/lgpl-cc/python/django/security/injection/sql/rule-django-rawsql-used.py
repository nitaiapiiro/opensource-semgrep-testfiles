from django.db import connection
from django.http import HttpResponse, Http404
from django.shortcuts import render, redirect
from django.template import loader

from .models import Person
from .forms import MyForm

import logging

logger = logging.getLogger(__name__)

from importlib import import_module
from django.conf import settings
from django.urls import URLResolver, URLPattern

def get_all_view_names(urlpatterns=None):
    if not urlpatterns:
        appurls = import_module('myapp.urls')
        urlpatterns = appurls.urlpatterns

    VIEW_NAMES = []  
    for pattern in urlpatterns:
        if isinstance(pattern, URLResolver):
            get_all_view_names(pattern.url_patterns)
        elif isinstance(pattern, URLPattern):
            view_name = pattern.callback.__name__
            VIEW_NAMES.append(view_name)
    return VIEW_NAMES

def testcase01_vulnerable(request, id):
    # late imports confuse semgrep while resolving fully qualified function calls
    # todoruleid: python_django_rule-django-rawsql-used
    RawSQL('SELECT count(*) FROM myapp_person WHERE name NOT LIKE "%s"' % id, [])

    return render(request, "root.html")

from django.db.models.expressions import RawSQL

def index(myrequest):
    mymodels = Person.objects.order_by("-birthdate")
    template = "myapp.html"

    myform = MyForm(myrequest.POST)
    myflag = False
    mymodel = None
    not_contained_count = None

    if myrequest.method == "POST":
        myflag = True
        myform.is_valid()
        if myform.cleaned_data["name"] != "":
            name = myform["name"].value()

            mymodels = Person.objects.annotate(
                not_contained_count=RawSQL(
                    # ruleid: python_django_rule-django-rawsql-used
                    'SELECT count(*) FROM myapp_person WHERE name NOT LIKE "%s"'
                    % name,
                    params=(),
                )
            )
            
            if len(mymodels)>0:
                mymodel = mymodels[0]
                not_contained_count = mymodel.not_contained_count

    all_views = get_all_view_names()
    logger.debug(f"[DEBUG] {all_views}")

    all_views.remove('index')
    all_views.remove('testcase17_vulnerable')

    context = {
        "views": all_views,
        "mymodels": mymodels,
        "mymodel": mymodel,
        "form": myform,
        "myflag": myflag,
        "not_contained_count": not_contained_count,
    }

    return render(myrequest, template, context)

def testcase02_vulnerable(request, id):
    # ruleid: python_django_rule-django-rawsql-used
    RawSQL('SELECT count(*) FROM myapp_person WHERE name NOT LIKE "%s"' % request, [])

    try:
        mymodels = Person.objects.annotate(
            models_with_higher_id=RawSQL(
                # ruleid: python_django_rule-django-rawsql-used
                'SELECT count(*) FROM myapp_person WHERE id > "%d"' % id,
                params=(),
            )
        )
        mymodel = mymodels.get(pk=id)
    except Person.DoesNotExist:
        raise Http404("Question does not exist")

    html = """
    <html>
        <body>
            <table>
                <tbody>
                    <tr>
                    <th>ID</th>
                    <td>%d</td>
                    </tr>
                    <tr>
                    <th>mymodel.name</th>
                    <td>%s</td>
                    </tr>
                    <tr>
                    <th>mymodel.models_with_higher_id</th>
                    <td>%d</td>
                </tbody>
                <table>
            </body>
    </html>""" % (
        id,
        mymodel.name,
        mymodel.models_with_higher_id,
    )

    return HttpResponse(html)

def myfunction(arg1, arg2, arg3=None):
    return None

def testcase03_vulnerable(req, id):

    mymodels = Person.objects.annotate(
        # ruleid: python_django_rule-django-rawsql-used
        not_contained_count=RawSQL('SELECT count(*) FROM myapp_person WHERE name NOT LIKE "%s"' % req, [])
    )

    mymodel = mymodels.get(pk=id)
    return render(req, "mymodel/detail.html", {"mymodel": mymodel})

def testcase04_vulnerable(req, id):

    mymodels = Person.objects.annotate(
        # ruleid: python_django_rule-django-rawsql-used
        not_contained_count=RawSQL('SELECT count(*) FROM myapp_person WHERE name NOT LIKE "%s"' % id, [])
    )
    mymodel = mymodels.get(pk=id)
    return render(req, "mymodel/detail.html", {"mymodel": mymodel})

def testcase05_safe(req, id):

    # ok: python_django_rule-django-rawsql-used
    mymodels = Person.objects.annotate(
        not_contained_count=RawSQL('SELECT count(*) FROM myapp_person WHERE name NOT LIKE "%s"' % "XXXX", [])
    )

    mymodel = mymodels.get(pk=id)
    return render(req, "mymodel/detail.html", {"mymodel": mymodel})

def testcase06_safe(req, id):

    query_param_string_literal = "this is a constant"
    query = 'SELECT count(*) FROM myapp_person WHERE name NOT LIKE "%s"' % query_param_string_literal
    # ok: python_django_rule-django-rawsql-used
    mymodels = Person.objects.annotate(not_contained_count=RawSQL(query, []))

    mymodel = mymodels.get(pk=id)
    return render(req, "mymodel/detail.html", {"mymodel": mymodel})

def testcase07_vulnerable(req, id):

    query_param_meta_get = req.META.get("HTTP_IDCLIENT", "")
    query = 'SELECT count(*) FROM myapp_person WHERE name NOT LIKE "' + query_param_meta_get + '"'
    mymodels = Person.objects.annotate(
        # ruleid: python_django_rule-django-rawsql-used
        not_contained_count=RawSQL(query, [])
    )
    mymodel = mymodels.get(pk=id)
    return render(req, "mymodel/detail.html", {"mymodel": mymodel})

def testcase08_vulnerable(req, id):
    query_param_meta_get = req.META.get("HTTP_IDCLIENT", "")
    query = 'SELECT count(*) FROM myapp_person WHERE name NOT LIKE "{}"'.format(query_param_meta_get)
    mymodels = Person.objects.annotate(
        # ruleid: python_django_rule-django-rawsql-used
        not_contained_count=RawSQL(query, [])
    )
    mymodel = mymodels.get(pk=id)

    return render(req, "mymodel/detail.html", {"mymodel": mymodel})

def testcase09_safe(req, id):

    param = "hello"
    query = f'SELECT count(*) FROM myapp_person WHERE name NOT LIKE "{param}"'
    # ok: python_django_rule-django-rawsql-used
    mymodels = Person.objects.annotate(not_contained_count=RawSQL(query, []))

    mymodel = mymodels.get(pk=id)
    return render(req, "mymodel/detail.html", {"mymodel": mymodel})

def testcase10_safe(req, id):

    param = "hello"
    # ok: python_django_rule-django-rawsql-used
    mymodels = Person.objects.annotate(
        not_contained_count=RawSQL(f'SELECT count(*) FROM myapp_person WHERE name NOT LIKE "{param}"', [])
    )

    mymodel = mymodels.get(pk=id)
    return render(req, "mymodel/detail.html", {"mymodel": mymodel})

def testcase11_safe(req, id):

    # ok: python_django_rule-django-rawsql-used
    mymodels = Person.objects.annotate(not_contained_count=RawSQL('SELECT count(*) FROM myapp_person', []))

    mymodel = mymodels.get(pk=id)
    return render(req, "mymodel/detail.html", {"mymodel": mymodel})

def testcase12_vulnerable(req, id):

    mymodel = Person.objects.get(pk=id)
    mymodel_text = mymodel.name
    mymodels = Person.objects.annotate(
        not_contained_count=RawSQL(
            # ruleid: python_django_rule-django-rawsql-used
            'SELECT count(*) FROM myapp_person WHERE name NOT LIKE "%s"' % mymodel_text,
            params=(),
        )
    )

    mymodel = mymodels.get(pk=id)
    return render(req, "mymodel/detail.html", {"mymodel": mymodel})

def testcase13_vulnerable(req, id):

    query_param_request_header = req.headers.get("User-Agent")
    mymodels = Person.objects.annotate(
        not_contained_count=RawSQL(
            # ruleid: python_django_rule-django-rawsql-used
            'SELECT count(*) FROM myapp_person WHERE name NOT LIKE "%s"'
            % query_param_request_header,
            params=(),
        )
    )

    mymodel = mymodels.get(pk=id)
    return render(req, "mymodel/detail.html", {"mymodel": mymodel})

def testcase14_safe(req, id):

    query_param_string_literal = "this is a constant"
    param = "{}".format(query_param_string_literal)

    mymodels = Person.objects.annotate(
        not_contained_count=RawSQL(
            # okruleid: python_django_rule-django-rawsql-used
            'SELECT count(*) FROM myapp_person WHERE name NOT LIKE "%s"' % param,
            params=(),
        )
    )
    mymodel = mymodels.get(pk=id)
    return render(req, "mymodel/detail.html", {"mymodel": mymodel})

def testcase15_safe(req, id):

    query_param_myfunction = myfunction("HTTP_IDCLIENT", None)
    mymodels = Person.objects.annotate(
        not_contained_count=RawSQL(
            # ok: python_django_rule-django-rawsql-used
            'SELECT count(*) FROM myapp_person WHERE name NOT LIKE "%s"'
            % query_param_myfunction,
            params=(),
        )
    )
    mymodel = mymodels.get(pk=id)
    return render(req, "mymodel/detail.html", {"mymodel": mymodel})

def testcase16_safe(req, id):
    mymodel = Person.objects.get(pk=id)
    return render(req, "mymodel/detail.html", {"mymodel": mymodel})

def testcase17_vulnerable(req, id, path):

    query = 'SELECT count(*) FROM myapp_person WHERE name NOT LIKE "%s"' % path
    logger.debug("[DEBUG] %s" % query)
    mymodels = Person.objects.annotate(
        # ruleid: python_django_rule-django-rawsql-used
        not_contained_count=RawSQL(query, [])
    )
    for model in mymodels:
        logger.debug("[!] %s" % model.not_contained_count)
    mymodel = mymodels.get(pk=id)

    return render(req, "mymodel/detail.html", {"mymodel": mymodel})
