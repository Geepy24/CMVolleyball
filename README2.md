<html lang="en"><head>
    <meta charset="UTF-8">
    <title></title>
<style id="system" type="text/css">h1,h2,h3,h4,h5,h6,p,blockquote {    margin: 0;    padding: 0;}body {    font-family: "Helvetica Neue", Helvetica, "Hiragino Sans GB", Arial, sans-serif;    font-size: 13px;    line-height: 18px;    color: #737373;    margin: 10px 13px 10px 13px;}a {    color: #0069d6;}a:hover {    color: #0050a3;    text-decoration: none;}a img {    border: none;}p {    margin-bottom: 9px;}h1,h2,h3,h4,h5,h6 {    color: #404040;    line-height: 36px;}h1 {    margin-bottom: 18px;    font-size: 30px;}h2 {    font-size: 24px;}h3 {    font-size: 18px;}h4 {    font-size: 16px;}h5 {    font-size: 14px;}h6 {    font-size: 13px;}hr {    margin: 0 0 19px;    border: 0;    border-bottom: 1px solid #ccc;}blockquote {    padding: 13px 13px 21px 15px;    margin-bottom: 18px;    font-family:georgia,serif;    font-style: italic;}blockquote:before {    content:"C";    font-size:40px;    margin-left:-10px;    font-family:georgia,serif;    color:#eee;}blockquote p {    font-size: 14px;    font-weight: 300;    line-height: 18px;    margin-bottom: 0;    font-style: italic;}code, pre {    font-family: Monaco, Andale Mono, Courier New, monospace;}code {    background-color: #fee9cc;    color: rgba(0, 0, 0, 0.75);    padding: 1px 3px;    font-size: 12px;    -webkit-border-radius: 3px;    -moz-border-radius: 3px;    border-radius: 3px;}pre {    display: block;    padding: 14px;    margin: 0 0 18px;    line-height: 16px;    font-size: 11px;    border: 1px solid #d9d9d9;    white-space: pre-wrap;    word-wrap: break-word;}pre code {    background-color: #fff;    color:#737373;    font-size: 11px;    padding: 0;}@media screen and (min-width: 768px) {    body {        width: 748px;        margin:10px auto;    }}</style><style id="custom" type="text/css"></style></head>
<body marginheight="0"><h2>一.说明</h2>
<h4>1.该项目使用Spring+Struts2+Hibernate5,注解+xml配置文件的形式搭建环境</h4>
<h4>2.由于本人对前端学习不深，因此只有网站首页是由自己完成，后台系统页面及个人用户页面均是由网上的开源项目更改而成，地址见附录。</h4>
<h4>3.此项目是抱着学习以及尝试不同方式的心态完成，因此风格不统一。例如：后台页面使用jsp，struts2的值栈完成前后台数据的交互；个人用户页面则使用的是html，使用jquery+ajax完成前后台的数据交互</h4>
<h4>4.由于在最初只想做个文章系统，没有考虑到需要那么多功能及审核机制，因此，数据库的表间联系略有不足，且程序中存在一些重复代码，后面将会慢慢完善。</h4>
<h4>5.本人在实践本项目的过程中，代码书写方式以及对整体性的思考都不断的有进步，所以可能导致前期写的代码与后期风格不同，可能衔接不好或者略显混乱。后面将会慢慢更改完善</h4>
<h4>6.一些细节将会慢慢完善处理</h4>
<h4>7.踩了不少的坑ORZ</h4>
<h2>二.主要功能</h2>
<h4>1.网站分为公共页面，管理员后台管理页面，用户个人页面</h4>
<h4>2.在公共页面中，已登陆的用户可以查看网站中的文章，在线视频以及图片内容。网站首页会自动填充新发布的文章</h4>
<h4>3.在个人页面中，用户可以发表文章，可以上传图片以及视频（需要审核），可以删除，修改文章</h4>
<h4>4.后台管理员系统中，管理员也可以发表文章，上传图片以及视频（不需要审核）</h4>
<h4>5.管理员在后台管理系统中审核用户上传的图片及视频，通过审核则可以显示到公共页面的视频及图片列表中</h4>
<h2>三.数据库表</h2>
<h2>四.一些业务逻辑的个人想法</h2>
<h2>五.附录</h2>
</body></html>