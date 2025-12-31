# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

class Product < ActiveRecord::Base
  def test_find_order
    #ruleid: ruby_sql_rule-CheckSQL
    Product.find(:all, :order => params[:order])
    #ruleid: ruby_sql_rule-CheckSQL
    Product.find(:all, :conditions => 'admin = 1', :order => "name #{params[:order]}")
  end

  def test_find_group
    #ruleid: ruby_sql_rule-CheckSQL
    Product.find(:all, :conditions => 'admin = 1', :group => params[:group])
    #ruleid: ruby_sql_rule-CheckSQL
    Product.find(:all, :conditions => 'admin = 1', :group => "something, #{params[:group]}")
  end

  def test_find_having
    #ruleid: ruby_sql_rule-CheckSQL
    Product.find(:first, :conditions => 'admin = 1', :having => "x = #{params[:having]}")

    #ok: ruby_sql_rule-CheckSQL
    Product.find(:first, :conditions => 'admin = 1', :having => { :x => params[:having]})

    #ok: ruby_sql_rule-CheckSQL
    Product.find(:first, :conditions => ['name = ?', params[:name]], :having => [ 'x = ?', params[:having]])

    #ruleid: ruby_sql_rule-CheckSQL
    Product.find(:first, :conditions => ['name = ?', params[:name]], :having => [ "admin = ? and x = #{params[:having]}", cookies[:admin]])
    #ruleid: ruby_sql_rule-CheckSQL
    Product.find(:first, :conditions => ['name = ?', params[:name]], :having => [ "admin = ? and x = '" + params[:having] + "'", cookies[:admin]])
  end

  def test_find_joins
    #ok: ruby_sql_rule-CheckSQL
    Product.find(:first, :conditions => 'admin = 1', :joins => "LEFT JOIN comments ON comments.post_id = id")

    #ruleid: ruby_sql_rule-CheckSQL
    Product.find(:first, :conditions => 'admin = 1', :joins => "LEFT JOIN comments ON comments.#{params[:join]} = id")

    #ok: ruby_sql_rule-CheckSQL
    Product.find(:first, :conditions => 'admin = 1', :joins => [:x, :y])

    #ruleid: ruby_sql_rule-CheckSQL
    Product.find(:first, :conditions => 'admin = 1', :joins => ["LEFT JOIN comments ON comments.#{params[:join]} = id", :x, :y])
  end

  def test_find_select
    #ok: ruby_sql_rule-CheckSQL
    Product.find(:last, :conditions => 'admin = 1', :select => "name")

    #ruleid: ruby_sql_rule-CheckSQL
    Product.find(:last, :conditions => 'admin = 1', :select => params[:column])
    #ruleid: ruby_sql_rule-CheckSQL
    Product.find(:last, :conditions => 'admin = 1', :select => "name, #{params[:column]}")
    #ruleid: ruby_sql_rule-CheckSQL
    Product.find(:last, :conditions => 'admin = 1', :select => "name, " + params[:column])
  end

  def test_find_from
    #ok: ruby_sql_rule-CheckSQL
    Product.find(:last, :conditions => 'admin = 1', :from => "users")

    #ruleid: ruby_sql_rule-CheckSQL
    Product.find(:last, :conditions => 'admin = 1', :from => params[:table])
    #ruleid: ruby_sql_rule-CheckSQL
    Product.find(:last, :conditions => 'admin = 1', :from => "#{params[:table]}")
  end

  def test_find_lock
    #ok: ruby_sql_rule-CheckSQL
    Product.find(:last, :conditions => 'admin = 1', :lock => true)

    #ruleid: ruby_sql_rule-CheckSQL
    Product.find(:last, :conditions => 'admin = 1', :lock => params[:lock])
    #ruleid: ruby_sql_rule-CheckSQL
    Product.find(:last, :conditions => 'admin = 1', :lock => "LOCK #{params[:lock]}")
  end

  def test_where
    #ok: ruby_sql_rule-CheckSQL
    Product.where("admin = 1")
    #ok: ruby_sql_rule-CheckSQL
    Product.where("admin = ?", params[:admin])
    #ok: ruby_sql_rule-CheckSQL
    Product.where(["admin = ?", params[:admin]])
    #ok: ruby_sql_rule-CheckSQL
    Product.where(["admin = :admin", { :admin => params[:admin] }])
    #ok: ruby_sql_rule-CheckSQL
    Product.where(:admin => params[:admin])
    #ok: ruby_sql_rule-CheckSQL
    Product.where(:admin => params[:admin], :some_param => params[:some_param])

    #ruleid: ruby_sql_rule-CheckSQL
    Product.where("admin = '#{params[:admin]}'").first
    #ruleid: ruby_sql_rule-CheckSQL
    Product.where(["admin = ? AND user_name = #{@name}", params[:admin]])
  end

  TOTALLY_SAFE = "some safe string"

  def test_constant_interpolation
    #ok: ruby_sql_rule-CheckSQL
    Product.first("blah = #{TOTALLY_SAFE}")
  end

  def test_local_interpolation
    #this is a weak finding and should be covered by a different rule
    #ok: ruby_sql_rule-CheckSQL
    Product.first("blah = #{local_var}")
  end

  def test_conditional_args_in_sql
    #ruleid: ruby_sql_rule-CheckSQL
    Product.last("blah = '#{something ? params[:blah] : TOTALLY_SAFE}'")

    #ok: ruby_sql_rule-CheckSQL
    Product.last("blah = '#{params[:blah] ? 1 : 0}'")
  
    #ruleid: ruby_sql_rule-CheckSQL
    Product.last("blah = '#{params[:blah] ? params[:blah] : 0}'")

    #ruleid: ruby_sql_rule-CheckSQL
    Product.last("blah = '#{params[:blah] ? 1 : params[:blah]}'")
  end

  def test_params_in_args
    #ruleid: ruby_sql_rule-CheckSQL
    Product.last("blah = '#{something(params[:blah])}'")
  end

  def test_params_to_i
    #ok: ruby_sql_rule-CheckSQL
    Product.last("blah = '#{params[:id].to_i}'")
  end

  def test_more_if_statements
    if some_condition
      x = params[:x]
    else
      x = "BLAH"
    end

    y = if some_other_condition
      params[:x]
      "blah"
    else
      params[:y]
      "blah"
    end

    #ruleid: ruby_sql_rule-CheckSQL
    Product.last("blah = '#{x}'")

    #ok: ruby_sql_rule-CheckSQL
    Product.last("blah = '#{y}'")
    #ok: ruby_sql_rule-CheckSQL
    Product.where("blah = 1").group(y)
  end

  def test_calculations
    #ruleid: ruby_sql_rule-CheckSQL
    Product.calculate(:count, :all, :conditions => "blah = '#{params[:blah]}'")
    #ruleid: ruby_sql_rule-CheckSQL
    Product.minimum(:price, :conditions => "blah = #{params[:blach]}")
    #ruleid: ruby_sql_rule-CheckSQL
    Product.maximum(:price, :group => params[:columns])
    #ruleid: ruby_sql_rule-CheckSQL
    Product.average(:price, :conditions => ["blah = #{params[:columns]} and x = ?", x])
    #ruleid: ruby_sql_rule-CheckSQL
    Product.sum(params[:columns])
  end

  def test_select
    #ok: ruby_sql_rule-CheckSQL
    Product.select([:price, :sku])

    #ruleid: ruby_sql_rule-CheckSQL
    Product.select params[:columns]
  end

  def test_conditional_in_options
    x = params[:x] == y ? "created_at ASC" : "created_at DESC"
    z = params[:y] == y ? "safe" : "totally safe"

    #ok: ruby_sql_rule-CheckSQL
    Product.all(:order => x, :having => z, :select => z, :from => z,
                :group => z)
  end

  def test_or_interpolation
    #ok: ruby_sql_rule-CheckSQL
    Product.where("blah = #{1 or 2}")
  end

  def test_params_to_f
    #ok: ruby_sql_rule-CheckSQL
    Product.last("blah = '#{params[:id].to_f}'")
  end

  def test_interpolation_in_first_arg
    #ruleid: ruby_sql_rule-CheckSQL
    Product.where("x = #{params[:x]} AND y = ?", y)
  end

  def test_to_sql_interpolation
    #ok: ruby_sql_rule-CheckSQL
    prices = Product.select(:price).where("created_at < :time").to_sql
    #ok: ruby_sql_rule-CheckSQL
    where("price IN (#{prices}) OR whatever", :price => some_price)
  end
end