# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

  def test_render
    @some_variable = params[:unsafe_input]
    # ok: ruby_file_rule-CheckRenderLocalFileInclude
    render :index
  end

  def test_dynamic_render
    page = params[:page]
    #ruleid: ruby_file_rule-CheckRenderLocalFileInclude
    render :file => "/some/path/#{page}"
  end

  def test_render_with_modern_param
    page = params[:page]
    #ruleid: ruby_file_rule-CheckRenderLocalFileInclude
    render file: "/some/path/#{page}"
  end

  def test_render_with_modern_param
    page = params[:page]
    #ok: ruby_file_rule-CheckRenderLocalFileInclude
    render file: File.basename("/some/path/#{page}")
  end

  def test_render_with_modern_param_second_param
    page = params[:page]
    #ruleid: ruby_file_rule-CheckRenderLocalFileInclude
    render status: 403, file: "/some/path/#{page}"
  end

  def test_render_with_old_param_second_param
    page = params[:page]
    #ruleid: ruby_file_rule-CheckRenderLocalFileInclude
    render :status => 403, :file => "/some/path/#{page}"
  end

  def test_render_with_first_positional_argument
    page = params[:page]
    #ruleid: ruby_file_rule-CheckRenderLocalFileInclude
    render page
  end

  def test_render_with_first_positional_argument_and_keyword
    page = params[:page]
    #ruleid: ruby_file_rule-CheckRenderLocalFileInclude
    render page, status: 403
  end

  def test_param_ok
    map = make_map
    thing = map[params.id]
    # ok: ruby_file_rule-CheckRenderLocalFileInclude
    render :file => "/some/path/#{thing}"
  end
    


  def test_render_static_template_name
    # ok: ruby_file_rule-CheckRenderLocalFileInclude
    render :update, locals: { username: params[:username] }
  end