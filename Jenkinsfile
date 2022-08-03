def mvn(args) {
    sh "mvn ${args} -Dmaven.repo.local=/github/workspace/.m2"
}
def mysh(cmd) {
    sh('#!/bin/sh -e\n' + cmd)
}
pipeline {
    agent any

    stages {
        stage('checkout code'){
            steps {
                mysh "cp -r /github/workspace/* $WORKSPACE"
            }
        }
        stage('Build') {
            steps {
                echo 'Building..'
                sh "mvn -v"
                mvn 'clean install -DskipTests=true -Dmaven.javadoc.skip=true -Dcheckstyle.skip=true -B -V'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
