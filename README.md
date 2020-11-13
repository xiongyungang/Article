# Article
# 20:00 2018/7/9
【表结构】
分类表   分类id   分类名称
用户表   用户id  昵称   密码  
文章表   文章id   标题   分类id   内容  用户 创建时间 (评论列表)
评论表   评论id  内容   评论时间   文章id  用户id 
收藏表	收藏id	文章id	用户id

【关系】
文章-分类  多对一  一对多
文章-用户  多对一  一对多
文章-评论  一对多  多对一  级联删除
用户-评论  一对多  多对一
文章-收藏  多对一	级联更新

【功能列表】
首页ok
文章列表ok
文章详细ok
编辑文章ok
发布文章ok
登陆23:30 2018/8/14
注册23:30 2018/8/14
用户评论点赞	2019/1/30
文章收藏21:47 2019/2/1
个人中心13:31 2019/5/5

下拉刷新：ajax获取指定用户的所有文章，随机
游客功能：
	获取所有
	根据分类获取

【会员功能】
	评论：评论表	根据文章id获取所有评论内容
	收藏：收藏表	根据用户查找收藏列表	根据用户判断文章是否收藏
	管理：文章编辑/删除，收藏删除（详细页面上做）
	根据用户获取
	根据用户和收藏获取
	根据用户和开始结束时间获取
	根据用户、时间、分类获取文章列表
	用户中心：
		个人信息、收藏文章、用户文章、个人相册
【角色】
	游客：
		浏览
	会员：
		浏览、评论、发布、修改、点赞

【fastdfs】
docker run -d --name storage --net=host -v /var/fdfs_data:/data/fast_data/data -e  TRACKER_IP=120.78.88.154:22122 -e GROUP_NAME=group1  morunchang/fastdfs sh storage.sh
docker run -d --name tracker --net=host  morunchang/fastdfs sh tracker.sh

# 问题和解决
【详细页面太短】
可以限制文章字数，或者固定div

【无文章的分类，分页数码错误】
22:39 2019/2/20
已解决，隐藏div方式

【控制器接受不了pojo套pojo，如Comment中Article】

【拦截器拦截不了ajax请求，没登陆评论的情况】
已经返回了login.html 没有自动跳转过去
暂时使用js判断

【thymeleaf在开发环境正常，但用jar运行时报错 Error resolving template template might not exist or might not be accessible】
解决方案：
（1）配置中添加  spring.thymeleaf.prefix=classpath:/templates
（2）指向模板的路径 不加 /

【跳转到login，没有跳回来】

【deleteMapping传递不了ajax中data数据】
使用type:post方式，在data中_method:"delete"即可

【文章删除】
用户1创建的文章被用户2收藏，导致用户1无法删除
 
【ajax提交失败】
将提交按钮botton放在form表单外面，奇迹发生了

【yml文件】
idea不识别yml文件：1没装插件2设置中File Types把yml识别为text了
yml文件从其他项目拷贝，编码不一样导致无法识别

【游客收藏未跳转到登陆页】

# thymeleaf
【标签】
th:href="{@|地址（或${}）|}"
th:text="${}"	替换文本内容
th:utext="${}"	替换html内容
th:onclick="'javascript:openBox(\''+${article.articleId}+'\')'"	执行带参函数
th:class="${pageNo eq articles?.number}?'active':'waves-effect'" 三元表达式
th:each="pageNo:${#numbers.sequence(0, articles?.totalPages-1)}" 指定循环次数
${Object?.value}	可传递null值
【引用】
<div th:fragment="head"></div>
<div th:replace="common::head"></div>



# Jquery
contains('text'):内容选择器，选择包含text文本的元素或子元素
【当前元素的子元素】
1.find():查找所有的子元素，会一直查找，跨层级查找；
2.children():查找直接的子元素，不会跨层级查找。
【attr()】
1.用于获取属性值:attr("class")
2.用于更改属性值:attr("class","red")



# pringDATA+JPA
【流程】
1.配置数据源
2.配置EntityManagerFactory
3.配置事务管理器
4.配置支持注解的事务
5.配置springdata
【Repository接口定义】
方式1：继承Repository<实体, 主键>接口
Repository接口:空接口，标记接口，没有包含方法声明的接口
方式2：@RepositoryDefinition(domainClass=实体.class,idClass=主键.class)
【查询方法规则】
name like ?% and age<?
findByNameStartingWithAndAgeLessThan

name like %? and age=?
findByNameEndingWithAndAgeEquals

name in (?,?,..) and age<?
findByNameInOrAgeLessThan

findArticleByUserAndCreateTimeBetweenOrderByCreateTimeDesc(user,date1,date2)	//根据用户和开始结束时间

【@Query注解】自定义查询
//根据分类查找文章		 	 文章表的分类对象    分类表主键 
SELECT a FROM Category c ,Article a WHERE a.category =   c.categoryId  and c.categoryName = 'java'
占位符1:
    @Query("select e from Employee e WHERE e.name = ?1 and e.age = ?2")
    public List<Employee> getByNameAndAge(String name,Integer age);
占位符2:
	@Query("select e from Employee e WHERE e.name=:name and e.age =:age")
	public List<Employee> getByNameAndAge2(@Param("name") String name, @Param("age") Integer age);
开启原始sql语句(表名为数据库表名)：
【事务】
1)事务一般在Service层使用（一个Service可能有多个dao的调用)
2）@Query,@Modifying,@Transactional结合使用
【Repository子接口】
1)CrudRepository:继承Repository,实现crud方法
2)PagingAndSortingRepository:继承CrudRepository，实现分页和排序
3)JpaRepository:继承PagingAndSortingRepository，实现jpa规范相关方法
【JpaSpecificationExecutor接口】
封装了JPA Criteria查询条件

/*******Hibernate关联映射注解*****
【@OneToOne】一对一的关系，只需在主控方（数据总表）内注明@OneToOne
【OneToMany和@ManyToOne】只需在被控方写mappedBy,其值为主控方中引用的外键对象的名称
【关联注解的属性】
1)fetch属性是该实体的加载方式
fetch=FetchType.LAZY为默认的数据延迟加载
fetch=FetchType.EAGER为急加载。
2)cascade属性表示与此实体一对一关联的实体的级联样式类型
CascadeType.PERSIST级联新增（又称级联保存）； 
CascadeType.MERGE:级联合并（级联更新）； 
CascadeType.REMOVE:级联删除； 
CascadeType.REFRESH:级联刷新 ；
CascadeType.ALL:全级联； 
3)mapperBy属性:指关系被维护端，双向关联必须设置此属性，因为双向关联只能交给一方去控制
【@JoinColumn】保存表与表之间关系的字段，被控方对应的主键，默认name = 关联表的名称+”-“+关联表主键的字段名

# SimpleDateFormat
设置指定格式的日期对象
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
Date date = sdf.parse("2018-7-1")




# shiro权限管理
【Subject】
Subject是通过SecurityManager安全管理器进行认证授权
【SecurityManager】
安全管理器，负责对所有subject进行认证、授权等
【relm】
SecurityManager进行安全认证需要通过realm获取用户权限数据


【权限】
anon:无需认证
authc:必须认证才可以访问
user:如果使用remember的功能才可以访问
perms:该资源必须得到资源权限才可以访问
roles:该资源必须得到角色权限才可以访问

【springboot】
1.配置类注入bean
A:Realm(实现AuthorizingRealm接口，编写认证授权逻辑）
B:SecurityManager（设置Realm）
C:ShiroFilterFactoryBean(设置SecurityManager,添加过滤器）
