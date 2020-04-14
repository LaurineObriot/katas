function Cell(string) {
	this.alive = string === '.';
	this.liveNeighbors = 0;
}

module.exports = Cell;