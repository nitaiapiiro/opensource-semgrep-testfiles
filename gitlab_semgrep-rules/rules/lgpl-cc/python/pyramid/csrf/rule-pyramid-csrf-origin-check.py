# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

from pyramid.csrf import CookieCSRFStoragePolicy

def includeme_bad(config):
    config.set_csrf_storage_policy(CookieCSRFStoragePolicy())
    # ruleid: python_pyramid_rule-pyramid-csrf-origin-check
    config.set_default_csrf_options(check_origin=False)
    flag = False
    # ruleid: python_pyramid_rule-pyramid-csrf-origin-check
    config.set_default_csrf_options(check_origin=flag)


def includeme_good(config):
    config.set_csrf_storage_policy(CookieCSRFStoragePolicy())
    # ok: python_pyramid_rule-pyramid-csrf-origin-check
    config.set_default_csrf_options(check_origin=True)
    flag = True
    # ok: python_pyramid_rule-pyramid-csrf-origin-check
    config.set_default_csrf_options(check_origin=flag)
