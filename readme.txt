1. 配置信息 config.properties
2. excel文件必须在第一行第一类单元格内标注[REQ]或者[RES]代表请求或者响应，具体参考模板test.xlsx
3. excel的sheet名称为请求类和响应类的名称，如果是服务端必须以RequestData或ResponseData结束
4. 运行主类Main



配置说明：
isClient: true 代表生成客户端的实体类， false代表生成服务端的实体类
reqparentClass：表示请求类继承成的实体类（全类名）
resparentClass：表示响应类要集成的实体类（全类名）
model.package：实体类的包名
controller.package：controller的包名
controller.parent.class：controller要继承的父类名（全类名）
inputFIle：要处理的excel文件名称，全路径
outPath：生成文件的存放位置