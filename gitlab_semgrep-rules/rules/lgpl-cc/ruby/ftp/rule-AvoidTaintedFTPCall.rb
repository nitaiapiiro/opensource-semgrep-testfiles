# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

require 'net/ftp'

def foo

  host = params[:host]
  # ruleid: ruby_ftp_rule-AvoidTaintedFTPCall
  ftp = Net::FTP.new(host)

  # ruleid: ruby_ftp_rule-AvoidTaintedFTPCall
  ftp = Net::FTP.open(params[:host])

  ftp = Net::FTP.new()
  # ruleid: ruby_ftp_rule-AvoidTaintedFTPCall
  ftp.connect(params[:host])

  # ruleid: ruby_ftp_rule-AvoidTaintedFTPCall
  ftp.get("/tmp/#{params[:file]}")

  # ruleid: ruby_ftp_rule-AvoidTaintedFTPCall
  ftp.getbinaryfile("/tmp/#{params[:file]}")

  # ruleid: ruby_ftp_rule-AvoidTaintedFTPCall
  ftp.gettextfile("/tmp/#{params[:file]}")

  # ruleid: ruby_ftp_rule-AvoidTaintedFTPCall
  ftp.put("/tmp/#{params[:file]}")

  # ruleid: ruby_ftp_rule-AvoidTaintedFTPCall
  ftp.putbinaryfile("/tmp/#{params[:file]}")

  # ruleid: ruby_ftp_rule-AvoidTaintedFTPCall
  ftp.puttextfile("/tmp/#{params[:file]}")

  # ruleid: ruby_ftp_rule-AvoidTaintedFTPCall
  ftp.delete("/tmp/#{params[:file]}")

  # ruleid: ruby_ftp_rule-AvoidTaintedFTPCall
  ftp.storlines(params[:cmd], "/tmp/log")

  # ruleid: ruby_ftp_rule-AvoidTaintedFTPCall
  ftp.storbinary(params[:cmd], "/tmp/log")

  # ruleid: ruby_ftp_rule-AvoidTaintedFTPCall
  ftp.sendcmd(params[:cmd])

  # ruleid: ruby_ftp_rule-AvoidTaintedFTPCall
  ftp.retrlines(params[:cmd])

  # ruleid: ruby_ftp_rule-AvoidTaintedFTPCall
  ftp.retrbinary(params[:cmd], 1024)

  # ok: ruby_ftp_rule-AvoidTaintedFTPCall
  ftp = Net::FTP.new("example.com")

  # ok: ruby_ftp_rule-AvoidTaintedFTPCall
  ftp = Net::FTP.open("example.com")

  ftp = Net::FTP.new()
  # ok: ruby_ftp_rule-AvoidTaintedFTPCall
  ftp.connect("example.com")

  # ok: ruby_ftp_rule-AvoidTaintedFTPCall
  ftp.get("/tmp/file")

  # ok: ruby_ftp_rule-AvoidTaintedFTPCall
  ftp.getbinaryfile("/tmp/file")

  # ok: ruby_ftp_rule-AvoidTaintedFTPCall
  ftp.gettextfile("/tmp/file")

  # ok: ruby_ftp_rule-AvoidTaintedFTPCall
  ftp.put("/tmp/file")

  # ok: ruby_ftp_rule-AvoidTaintedFTPCall
  ftp.putbinaryfile("/tmp/file")

  # ok: ruby_ftp_rule-AvoidTaintedFTPCall
  ftp.puttextfile("/tmp/file")

  # ok: ruby_ftp_rule-AvoidTaintedFTPCall
  ftp.delete("/tmp/file")

  # ok: ruby_ftp_rule-AvoidTaintedFTPCall
  ftp.storlines("ls -al", "/tmp/log")

  # ok: ruby_ftp_rule-AvoidTaintedFTPCall
  ftp.storbinary("ls -al", "/tmp/log")

  # ok: ruby_ftp_rule-AvoidTaintedFTPCall
  ftp.sendcmd("ls -al")

  # ok: ruby_ftp_rule-AvoidTaintedFTPCall
  ftp.retrlines("ls -al")

end