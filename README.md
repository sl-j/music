# music
第一个项目(后端是springboot,数据库使用的mysql，前端使用vue框架，此项目为练手项目)
主要功能：
    后台管理：用户管理、歌曲管理、歌单管理、歌手管理（大部分都是crud操作，歌曲和图片信息直接放在项目文件中）
    前台管理：用户信息的修改，歌单评价，歌单和歌曲收藏、评分等功能
    数据库分成了用户表、管理员表、歌单表、歌曲表、歌手表、评分表、评论表等。针对多对多的关系，使用的是中间表
第一个springboot项目，很粗糙，还有许多需要改进的地方,在后续学习中，会再回头尝试解决这些问题 1.分页bug 2.查询bug：查询数量由后端来 3.后台安全访问bug 4.修改歌手信息是，若简介没有值，默认为null 6.用户评价在刷新后，评分的星星自己看不见，歌单重复刷新则为出现null 7.用户点赞后，刷新页面之后。无法保持用户点赞之后的状态颜色 8.歌单设置为默认封面时，翻页后，无法获取歌单的图片
