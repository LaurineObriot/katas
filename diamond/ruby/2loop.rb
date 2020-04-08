require 'webrick'

root   = File.path '/tmp/public_html'
server = WEBrick::HTTPServer.new :Port => 5000, :DocumentRoot => root

trap 'INT' do server.shutdown end
server.start
