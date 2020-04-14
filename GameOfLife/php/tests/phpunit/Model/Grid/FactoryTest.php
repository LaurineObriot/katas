<?php
/**
 * Created by PhpStorm.
 * User: jonatan
 * Date: 04/12/2018
 * Time: 18:35
 */

namespace App\Test\Model;

use App\Model\Cell;
use App\Model\Grid;
use App\Test\Model\Grid\GridHelper;
use PHPUnit\Framework\TestCase;

class FactoryTest extends GridHelper
{

    public function testFactoryConstructor()
    {
        $tests = [
            [
                'x'       => 3,
                'y'       => 1,
                'default' => Cell::ALIVE,
                'count'   => 3,
                'alive'   => 3,
            ],
            [
                'x'       => 1,
                'y'       => 5,
                'default' => Cell::DEAD,
                'count'  => 5,
                'alive'   => 0,
            ],
            [
                'x'       => 2,
                'y'       => 2,
                'default' => Cell::DEAD,
                'count'  => 4,
                'alive'   => 0,
            ],
            [
                'x'       => 2,
                'y'       => 2,
                'default' => Cell::ALIVE,
                'count'  => 4,
                'alive'   => 4,
            ],
        ];

        foreach($tests as $test){
            $grid = Grid::factory($test['x'],$test['y'],$test['default']);
            $this->assertEquals($test['count'],$grid->count());
            $this->assertEquals($test['alive'],$grid->alive());
        }
    }
}
