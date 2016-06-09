# Utils
常用工具类
#添加身份证号认证的工具类
    matcherPhoneNum 判断手机号格式 (匹配11数字,并且13-19开头)
    matcherAccount 判断账号格式 (4-20位字符)
    matcherPassword 判断密码格式 (6-12位字母或数字)
    matcherPassword2 判断密码格式 (6-12位字母或数字,必须同时包含字母和数字)
    matcherEmail 判断邮箱格式
    matcherVehicleNumber 判断中国民用车辆号牌
    matcherIdentityCard 判断身份证号码格式
    matcherRealName 判断姓名格式
        真实姓名可以是汉字,也可以是字母,但是不能两者都有,也不能包含任何符号和数字
         1.如果是英文名,可以允许英文名字中出现空格
         2.英文名的空格可以是多个,但是不能连续出现多个
         3.汉字不能出现空格
