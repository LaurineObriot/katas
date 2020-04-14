let uniq = require('lodash').uniq;

function GridValidator(){
  this.maxRows;
  this.maxCols;
}

GridValidator.prototype.validate = function(grid){
  this.maxRows = grid.length;
	return this._validateElements(grid) && this._validateRows() && this._validateColumns(grid);
};

GridValidator.prototype._validateRows = function(){
  return this.maxRows >= 1;
};

GridValidator.prototype._validateColumns = function(grid){
  let max;
  this.maxCols = max = grid[0].length;

  let consistantColumns = grid.every(function(subArray){
    return subArray.length === max;
  });

  return consistantColumns && this.maxCols >= 1;
};

GridValidator.prototype._validateElements = function(array) {
	let uniqueArray = uniq([].concat.apply([], array)).sort();
	return uniqueArray.every(ele => ele === '0' || ele === '.');
};

module.exports = GridValidator;