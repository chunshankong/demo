
//执行脚本
print("在js脚本中，调用Java内核程序实现的alert函数");

var Demo = Java.type('script.phase1.Demo');


Demo.alert('from js');


var OpenGL = Java.type('script.phase1.OpenGL');

OpenGL.fillRect(500,500,300,200,"green");