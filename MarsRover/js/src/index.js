const { GameController } = require('./controllers/controller')
const { runtimes } = require('./config')

var controller = new GameController(10, runtimes[1])
controller.start()