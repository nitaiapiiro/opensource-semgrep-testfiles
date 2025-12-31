# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

def test_send_file
    # ruleid: ruby_file_rule-CheckSendFile
    send_file params[:file]
end

def test_send_file2
    # ruleid: ruby_file_rule-CheckSendFile
    send_file cookies[:something]
end

def test_send_file3
    # ruleid: ruby_file_rule-CheckSendFile
    send_file cookies.permanent[:something]
end

def test_send_file4
    # ruleid: ruby_file_rule-CheckSendFile
    send_file cookies.permanent[:something]
end

def test_send_file5
    # ok: ruby_file_rule-CheckSendFile
    send_file cookies.encrypted[:something]
end

def test_send_file6
    # this is reported since semgrep 0.94 because . ... . can now match
    # intermediate fields, not just method calls.
    # ruleid: ruby_file_rule-CheckSendFile
    send_file cookies.signed.permanent[:something]
end

def test_send_file7
    # ok: ruby_file_rule-CheckSendFile
    send_file cookies.permanent.signed[:something]
end

def test_send_file8
    # ruleid: ruby_file_rule-CheckSendFile
    send_file request.env[:badheader]
end

def test_send_file_ok
    # ok: ruby_file_rule-CheckSendFile
    send_file "some_safe_file.txt"
end