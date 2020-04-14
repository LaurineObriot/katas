
let Cell = require('../src/cell');

describe("Cell", () => {
		let cell; 

	beforeEach(() => {
		cell = new Cell('.');
	});

	it("Should be able to be initialized", () => {
		expect(cell).toBeDefined();
	});

	it("Should be initialized as alive", () => {
		expect(cell.alive).toBe(true);
	});

	it("Should be initialized as not alive", () => {
		cell = new Cell('x');
		expect(cell.alive).toBe(false);
	});

	it("Should have 0 live neighbours", () => {
		expect(cell.liveNeighbors).toBe(0);
	});

});