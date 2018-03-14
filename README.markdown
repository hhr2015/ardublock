ArduBlock4fpga
======

这个项目来自[ArduBlock](https://github.com/taweili/ardublock)，我希望在它的基础上实现图形化生成verilog-based的神经网络IP核，让更多的人能使用FPGA来加速神经网络相关的计算。
This project comes from [ArduBlock](https://github.com/taweili/ardublock), and I hope to realize the graphical transformation into verilog-based neural network IP core based on it, so that more people can use FPGA to accelerate the calculation of neural network.

ArduBlock is a Block Programming Language for Arduino. The language and functions model closely to [Arduino Language Reference](http://arduino.cc/en/Reference/HomePage)

Please visit [ArduBlock git](https://github.com/taweili/ardublock) for more information



一个神经网络将可以这样表达：(Inception Residual Network v2)
A neural Network can be expressed in this way :(Inception Residual Network v2)
![Alt text](https://github.com/hhr2015/ardublock4fpga/raw/master/example/Inception_Residual_Network_v2.png)

FPGA部分还在设计中。
The FPGA section is still in the design.

ToDo
----
* 根据神经网络的结构参数来调整blocks的形状和接口
Adjust the shape and interface of blocks according to the structural parameters of the neural network.

* 根据blocks的组合，生成相应的模型文件
Generate the corresponding model file according to blocks' combination.

* 编写和调试基于verilog的神经网络代码，包括正向传播和反向传播
Write and debug the verilog-based neural network code, including training and estimation.

* 使用模板引擎vecloity，根据模型文件生成相应的verilog代码
Use the template engine vecloity to generate the corresponding verilog code according to the model file.

Compile 
----
* download [eclipse](https://www.eclipse.org/downloads/eclipse-packages/)
* File -> import -> Existing Maven Project
* Project -> Java Build Path -> Libraries -> Add External JARs, (ardublock/lib)
* Maven -> Update Project
* run

Authors
----
* David Li taweili@gmail.com
* HE Qichen heqichen@gmail.com
* Hu HaiRong huhairong2017@gmail.com

License
----

Copyright (C) 2011 David Li , He Qichen and Hu HaiRong

This file is part of ArduBlock.

ArduBlock is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

ArduBlock is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with ArduBlock.  If not, see <http://www.gnu.org/licenses/>.
