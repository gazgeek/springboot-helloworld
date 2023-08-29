def project_name='jenkins01'
def tag = "latest"

// 执行脚本
node {
    stage('拉取代码') {
         git credentialsId: 'be908ce3-b7d6-425f-b76a-e580044axxxx', url: 'https://gitee.com/xxx/demo_jenkins.git'
    }
	stage('工程编译'){
        //编译，构建
        sh """
            PATH=/usr/localhost/mavenxxx/bin:$PATH
            mvn clean package -Dmaven.test.skip=true
        """
	}
	stage('镜像打包'){
	   // 镜像打包
	  sh 'pwd'
	  sh 'docker build -t jenkins01:latest .'
	}
	stage('docker的部署'){
	     //镜像名称
         def imageName = "${project_name}:${tag}"
         //删除原有容器
         sh "docker rm -f ${project_name}"
         //容器加一层挂载目录 启动容器
         sh "docker run -id --name ${project_name} -p 9000:9000 ${imageName}"
	}
}
