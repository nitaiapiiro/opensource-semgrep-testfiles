# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

def foo

    # Test bad open combinations

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    File.open("/tmp/#{params[:name]}")
    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    File.open(params[:name])
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    File.open("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    Dir.open("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    Dir.open("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    IO.open("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    IO.open("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    Kernel.open("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    Kernel.open("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    PStore.open("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    PStore.open("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    Pathname.open("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    Pathname.open("/tmp/usr/bin")

    #
    # Test bad chdir combinations

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    File.chdir("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    File.chdir("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    Dir.chdir("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    Dir.chdir("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    IO.chdir("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    IO.chdir("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    Kernel.chdir("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    Kernel.chdir("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    PStore.chdir("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    PStore.chdir("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    Pathname.chdir("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    Pathname.chdir("/tmp/usr/bin")

    #
    # Test bad chroot combinations

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    File.chroot("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    File.chroot("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    Dir.chroot("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    Dir.chroot("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    IO.chroot("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    IO.chroot("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    Kernel.chroot("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    Kernel.chroot("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    PStore.chroot("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    PStore.chroot("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    Pathname.chroot("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    Pathname.chroot("/tmp/usr/bin")

    #
    # Test bad delete combinations

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    File.delete("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    File.delete("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    Dir.delete("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    Dir.delete("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    IO.delete("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    IO.delete("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    Kernel.delete("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    Kernel.delete("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    PStore.delete("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    PStore.delete("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    Pathname.delete("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    Pathname.delete("/tmp/usr/bin")

    #
    # Test bad lchmod combinations

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    File.lchmod("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    File.lchmod("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    Dir.lchmod("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    Dir.lchmod("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    IO.lchmod("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    IO.lchmod("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    Kernel.lchmod("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    Kernel.lchmod("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    PStore.lchmod("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    PStore.lchmod("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    Pathname.lchmod("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    Pathname.lchmod("/tmp/usr/bin")

    #
    # Test bad open combinations

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    File.open("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    File.open("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    Dir.open("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    Dir.open("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    IO.open("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    IO.open("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    Kernel.open("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    Kernel.open("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    PStore.open("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    PStore.open("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    Pathname.open("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    Pathname.open("/tmp/usr/bin")

    #
    # Test bad readlines combinations

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    File.readlines("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    File.readlines("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    Dir.readlines("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    Dir.readlines("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    IO.readlines("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    IO.readlines("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    Kernel.readlines("/tmp/#{params[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    Kernel.readlines("/tmp/usr/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    PStore.readlines("/tmp/#{cookies[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    PStore.readlines("/tmp/#{anything}/bin")

    # ruleid: ruby_file_rule-AvoidTaintedFileAccess
    Pathname.readlines("/tmp/#{request.env[:name]}")
    # ok: ruby_file_rule-AvoidTaintedFileAccess
    Pathname.readlines("/tmp/#{anything}/bin")


    #
    # Test ok tainted calls

    # ok: ruby_file_rule-AvoidTaintedFileAccess
    File.basename("/tmp/#{params[:name]}")

end