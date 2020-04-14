<?php
/**
 * Created by PhpStorm.
 * User: jonatan
 * Date: 6/12/18
 * Time: 19:36
 */

namespace App\Test\Model\Cell;


use App\Model\Cell;
use App\Model\Neighbours;
use PHPUnit\Framework\TestCase;

class CellHelper extends TestCase
{

    const DEAD = false;
    const ALIVE = true;
    /**
     * @return Cell
     */
    protected function getADeadCell(): Cell
    {
        $cell =  new Cell(false);

        $this->assertFalse($cell->isAlive());

        return $cell;
    }

    /**
     * @return Cell
     */
    protected function getAnAliveCell(): Cell
    {
        $cell =  new Cell(true);

        $this->assertTrue($cell->isAlive());

        return $cell;
    }


    protected function cellGenerationIsOk(Cell $cell,$numberOfNeighbours,$alive)
    {
        $neighbours = $this->getMockNeighbours();
        $neighbours->method('alive')->willReturn($numberOfNeighbours);
        $cell = $cell->nextGeneration($neighbours);
        $this->assertEquals($alive,$cell->isAlive());
    }


    /**
     * @return \PHPUnit\Framework\MockObject\MockObject
     */
    protected function getMockNeighbours(): \PHPUnit\Framework\MockObject\MockObject
    {
        $neighbours = $this->getMockBuilder(Neighbours::class)
                           ->disableOriginalConstructor()
                           ->getMock();

        return $neighbours;
    }

}