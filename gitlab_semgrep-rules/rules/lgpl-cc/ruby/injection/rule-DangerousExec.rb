# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

require 'open3'

def test_params()
  user_input = params['some_key']
# ruleid: ruby_injection_rule-DangerousExec
  exec("ls -lah #{user_input}")

# ruleid: ruby_injection_rule-DangerousExec
  Process.spawn([user_input, "smth"])

# ruleid: ruby_injection_rule-DangerousExec
  output = exec(["sh", "-c", user_input])

# ruleid: ruby_injection_rule-DangerousExec
  pid = spawn(["bash", user_input])

  commands = "ls -lah /raz/dva"
# ok: ruby_injection_rule-DangerousExec
  system(commands)

  cmd_name = "sh"
# ok: ruby_injection_rule-DangerousExec
  Process.exec([cmd_name, "ls", "-la"])
# ok: ruby_injection_rule-DangerousExec
  Open3.capture2({"FOO" => "BAR"}, [cmd_name, "smth"])
# ok: ruby_injection_rule-DangerousExec
  system("ls -lah /tmp")
# ok: ruby_injection_rule-DangerousExec
  exec(["ls", "-lah", "/tmp"])
end

def test_calls(user_input)
  # ruleid: ruby_injection_rule-DangerousExec
    exec("ls -lah #{user_input}")
  
  # ruleid: ruby_injection_rule-DangerousExec
    Process.spawn([user_input, "smth"])
  
  # ruleid: ruby_injection_rule-DangerousExec
    output = exec(["sh", "-c", user_input])
  
  # ruleid: ruby_injection_rule-DangerousExec
    pid = spawn(["bash", user_input])
  
    commands = "ls -lah /raz/dva"
  # ok: ruby_injection_rule-DangerousExec
    system(commands)
  
    cmd_name = "sh"
  # ok: ruby_injection_rule-DangerousExec
    Process.exec([cmd_name, "ls", "-la"])
  # ok: ruby_injection_rule-DangerousExec
    Open3.capture2({"FOO" => "BAR"}, [cmd_name, "smth"])
  # ok: ruby_injection_rule-DangerousExec
    system("ls -lah /tmp")
  # ok: ruby_injection_rule-DangerousExec
    exec(["ls", "-lah", "/tmp"])
  end

  def test_params()
    user_input = params['some_key']
  # ruleid: ruby_injection_rule-DangerousExec
    exec("ls -lah #{user_input}")
  
  # ruleid: ruby_injection_rule-DangerousExec
    Process.spawn([user_input, "smth"])
  
  # ruleid: ruby_injection_rule-DangerousExec
    output = exec(["sh", "-c", user_input])
  
  # ruleid: ruby_injection_rule-DangerousExec
    pid = spawn(["bash", user_input])
  
    commands = "ls -lah /raz/dva"
  # ok: ruby_injection_rule-DangerousExec
    system(commands)
  
    cmd_name = "sh"
  # ok: ruby_injection_rule-DangerousExec
    Process.exec([cmd_name, "ls", "-la"])
  # ok: ruby_injection_rule-DangerousExec
    Open3.capture2({"FOO" => "BAR"}, [cmd_name, "smth"])
  # ok: ruby_injection_rule-DangerousExec
    system("ls -lah /tmp")
  # ok: ruby_injection_rule-DangerousExec
    exec(["ls", "-lah", "/tmp"])
  end
  
  def test_cookies()
    user_input = cookies['some_cookie']
    # ruleid: ruby_injection_rule-DangerousExec
      exec("ls -lah #{user_input}")
    
    # ruleid: ruby_injection_rule-DangerousExec
      Process.spawn([user_input, "smth"])
    
    # ruleid: ruby_injection_rule-DangerousExec
      output = exec(["sh", "-c", user_input])
    
    # ruleid: ruby_injection_rule-DangerousExec
      pid = spawn(["bash", user_input])
    
      commands = "ls -lah /raz/dva"
    # ok: ruby_injection_rule-DangerousExec
      system(commands)
    
      cmd_name = "sh"
    # ok: ruby_injection_rule-DangerousExec
      Process.exec([cmd_name, "ls", "-la"])
    # ok: ruby_injection_rule-DangerousExec
      Open3.capture2({"FOO" => "BAR"}, [cmd_name, "smth"])
    # ok: ruby_injection_rule-DangerousExec
      system("ls -lah /tmp")
    # ok: ruby_injection_rule-DangerousExec
      exec(["ls", "-lah", "/tmp"])
    end