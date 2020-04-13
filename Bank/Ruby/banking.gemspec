# coding: utf-8
lib = File.expand_path("../lib", __FILE__)
$LOAD_PATH.unshift(lib) unless $LOAD_PATH.include?(lib)
require "banking/version"

Gem::Specification.new do |s|
  s.name = "banking"
  s.version = Banking::VERSION
  s.authors = ["costajob"]
  s.email = ["costajob@gmail.com"]
  s.summary = "The Ruby implementation of the banking code kata."
  s.homepage = "https://bitbucket.org/costajob/banking"
  s.files = `git ls-files -z`.split("\x0").reject { |f| f.match(%r{^(test|spec|features)/}) }
  s.bindir = "bin"
  s.executables << "banking" 
  s.require_paths = ["lib"]
  s.required_ruby_version = ">= 2.3.1"
  s.add_development_dependency "bundler", "~> 1.16"
  s.add_development_dependency "rake", "~> 12.3"
  s.add_development_dependency "minitest", "~> 5.1"
end
