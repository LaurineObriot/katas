<?php
/**
 * Created by PhpStorm.
 * User: jonatan
 * Date: 04/12/2018
 * Time: 18:35
 */

namespace App\Test\Model;

use App\Model\Grid;
use App\Test\Model\Grid\GridHelper;
use PHPUnit\Framework\TestCase;

class GetNeighboursTest extends GridHelper
{
    public function testMatrixWithOnlyOneCell()
    {
        $originalCellsState = file_get_contents(__DIR__ . '/../../Data/Input/1x1Cells.txt');
        $grid = Grid::readFromString($originalCellsState);
        $this->assertEquals(1,$grid->count());
        $cell = $grid->getCell(0,0);
        $neighbours = $grid->getNeighbours($cell);

        $this->assertEquals(0,$neighbours->count());
    }


    public function testSquare2x2()
    {
        $grid = Grid::factory(2, 2);

        $cell = $grid->getCell(0,0);
        $neighbours = $grid->getNeighbours($cell);

        $this->assertEquals(3, $neighbours->count());
    }

    public function testSquare5x5()
    {
        $grid = Grid::factory(5, 5);

        $cell = $grid->getCell(2,2);
        $neighbours = $grid->getNeighbours($cell);
        $this->assertEquals(8, $neighbours->count());

        $cell = $grid->getCell(0,0);
        $neighbours = $grid->getNeighbours($cell);
        $this->assertEquals(3, $neighbours->count());

        $cell = $grid->getCell(0,1);
        $neighbours = $grid->getNeighbours($cell);
        $this->assertEquals(5, $neighbours->count());
    }

    public function testMatrixWithSquareFormat()
    {
        $originalCellsState = file_get_contents(__DIR__ . '/../../Data/Input/2x2Cells.txt');
        $grid = Grid::readFromString($originalCellsState);
        $cell = $grid->getCell(0,0);

        $neighbours = $grid->getNeighbours($cell);
        $this->assertEquals(3, $neighbours->count());
        $this->assertEquals(1, $neighbours->alive());

    }


}
