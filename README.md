##用到的命令
1.给仓库创建密钥
首先用git bash输入以下命令，看本地是否存在密钥<br>
cd ~/.ssh<br>
ls<br>
如果存在id_rsagit和id_rsa.pub说明存在密钥<br>
2.复制密钥内容<br>
clip < ~/.ssh/id_rsa.pub<br>
3.然后直接在github上的仓库粘贴刚刚复制的密钥，注意不要有空格换行<br>
如果第一步的时候不存在那两个文件，需要我们生成文件<br>
命令：ssh-keygen<br>
之后会让你输入文件的名字，我们可以直接回车，使用默认名，然后会让你输入两次push代码时用到的密码，不写的话push代码的时候不用输入密码<br>
4.在idea中将代码提交到github用到的命令<br>
&nbsp;&nbsp;&nbsp;&nbsp;git init&nbsp;将项目初始化为git项目<br>
&nbsp;&nbsp;&nbsp;&nbsp;git status&nbsp;查看状态<br>
&nbsp;&nbsp;&nbsp;&nbsp;git add .&nbsp;暂存代码<br>
&nbsp;&nbsp;&nbsp;&nbsp;git commit -m "这里是注释"&nbsp;提交<br>
在提交的时候会提示让你tell me who you are,其实这里是让你配置用户名和邮箱<br>
git config --global user.email "you@example.com"<br>
git config --global user.name "Your Name"<br>
windows用到的cmd命令 dir显示文件 dir /a:h 显示隐藏文件<br>
我们的git config文件是隐藏文件，如果你用linux那就非常方便，直接vim 修改.git/config文件<br>
运行以下两行命令，这样我们的代码就提交到github了<br>
git remote add origin git@github.com:yidou120/community.git<br>
git push -u origin master

