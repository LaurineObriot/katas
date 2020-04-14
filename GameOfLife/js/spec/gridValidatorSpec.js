let GridValidator = require('../src/gridValidator');

describe('GridValidator', () => {
	let gridValidator = new GridValidator(), 
			grid;

    it('should return false if grid does not have consistent columns ', () => {
        grid = [
            ['.','.','.','.','.','.','.','.'],
            ['.','.','.','.','.','.','.','.'],
            ['.','.','.','.','.','.','.'],
            ['.','.','.','.','.','.','.','.'],
            ['.','.','.','.','.','.','.','.']
        ];

        expect(gridValidator._validateColumns(grid)).toEqual(false);
    });

  it('should return false if grid does not contain any content', () => {
    grid = [[]];

    expect(gridValidator._validateColumns(grid)).toEqual(false);
  });


	it('should return false if a valid grid size has invalid characters in it', () => {
		grid = [
			['.','.','.','.','.','.','.','.'],
			['.','.','.','.','.','.','.','.'],
			['.','.','.','.','.','.','.','.'],
			['.','.','.','.','A','.','.','.'],
			['.','.','.','.','.','.','.','.'],
			['.','.','.','.','.','.','.','.']
			];

		expect(gridValidator._validateElements(grid)).toEqual(false);
	});

    it('should return true if a valid grid  of any size is submitted', () => {
        grid = [
            ['.','.','.','.','.','.','.','.'],
            ['.','.','.','.','.','.','.','.'],
            ['.','.','.','.','.','.','.','.'],
            ['.','.','.','.','.','.','.','.'],
            ['.','.','.','.','.','.','.','.'],
            ['.','.','.','.','.','.','.','.'],
            ['.','.','.','.','.','.','.','.'],
            ['.','.','.','.','.','.','.','.'],
            ['.','.','.','.','.','.','.','.'],
            ['.','.','.','.','.','.','.','.'],
            ['.','.','.','.','.','.','.','.'],
            ['.','.','.','.','.','.','.','.'],
            ['.','.','.','.','.','.','.','.'],
            ['.','.','.','.','.','.','.','.'],
            ['.','.','.','.','.','.','.','.'],
            ['.','.','.','.','.','.','.','.'],
            ['.','.','.','.','.','.','.','.'],
            ['.','.','.','.','.','.','.','.']
        ];
        spyOn(gridValidator, '_validateElements').and.callThrough();
        spyOn(gridValidator, '_validateColumns').and.callThrough();
        spyOn(gridValidator, '_validateRows').and.callThrough();

        expect(gridValidator.validate(grid)).toEqual(true);
        expect(gridValidator._validateElements).toHaveBeenCalled();
        expect(gridValidator._validateRows).toHaveBeenCalled();
        expect(gridValidator._validateColumns).toHaveBeenCalled();

    });
});