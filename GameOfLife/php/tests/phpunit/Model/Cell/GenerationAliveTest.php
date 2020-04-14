<?php
/**
 * Created by PhpStorm.
 * User: jonatan
 * Date: 04/12/2018
 * Time: 17:12
 */

namespace App\Test\Model\Cell;



use App\Model\Neighbours;

class GenerationAliveTest extends CellHelper
{
    private $aliveCell;

    protected function setUp()
    {
        parent::setUp(); // TODO: Change the autogenerated stub
        $this->aliveCell =  $this->getAnAliveCell();
    }

    public function testUnderpopulation()
   {
       $populationForUnderPopulation = [0, 1];
       foreach($populationForUnderPopulation as $numberOfNeighbours) {
           $this->cellGenerationIsOk($this->aliveCell , $numberOfNeighbours, self::DEAD);
       }
   }

   public function testOvercrowding()
   {
       $populationForOverCrowding = [4, 5, 6, 7, 8 ];
       foreach($populationForOverCrowding as $numberOfNeighbours) {
           $this->cellGenerationIsOk($this->aliveCell , $numberOfNeighbours, self::DEAD);
       }
   }

   public function testCellContinuesAlive()
   {
       $populations = [2,3];

       foreach($populations as $numberOfNeighbours) {
           $this->cellGenerationIsOk($this->aliveCell , $numberOfNeighbours, self::ALIVE);
       }

   }



}
