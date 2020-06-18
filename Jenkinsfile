pipeline {
  agent {
    kubernetes {
      label 'todos-api-build'
      defaultContainer 'jnlp'
      yaml """
      apiVersion: v1
      kind: Pod
      metadata:
      labels:
        component: ci
      spec:
        containers:
        - name: maven
          image: maven:latest
          command:
          - cat
          tty: true
        - name: docker
          image: docker:latest
          command:
          - cat
          tty: true
          env:
          - name: DOCKER_API_VERSION
            value: "1.39"
          volumeMounts:
          - mountPath: /var/run/docker.sock
            name: docker-sock
        volumes:
          - name: docker-sock
            hostPath:
              path: /var/run/docker.sock
      """
    }
  }
  environment {
    NOTIFY_EMAIL = """${sh(
        returnStdout: true,
        script: "git --no-pager show -s --format='%ae'"
    )}"""
    CURRENT_GIT_BRANCH = """${sh(
       returnStdout: true,
       script: "git rev-parse --abbrev-ref HEAD"
   )}"""
    GCR_BASE_URL = 'https://gcr.io'
    GCR_REPO = 'gcr.io/caliber-dev-254513/todos-api'
    SERVICE_NAME = 'Category'
  }
  stages {
    stage('Testing') {
      steps {
        container('maven') {
          sh """
             mvn test
          """
        }
      }
    }
    stage('Baking') {
      when {
        branch 'master'
      }
      steps {
        container('docker') {
          withCredentials([file(credentialsId: 'GC_KEY', variable: 'GC_KEY')]) {
            sh 'cat $GC_KEY | docker login -u _json_key --password-stdin ${GCR_BASE_URL}'
            sh 'docker build . -t ${GCR_REPO}'
            sh 'docker push ${GCR_REPO}'
          }
        }
      }
    }
  }
  post {
    regression {
      mail to: "${NOTIFY_EMAIL}",
           subject: "${SERVICE_NAME} Service Broke",
           body: "Your changes to ${SERVICE_NAME} have caused an issue. Check the status and logs here: ${BUILD_URL}"
    }
    fixed {
      mail to: "${NOTIFY_EMAIL}",
           subject: "${SERVICE_NAME} Service Repaired",
           body: "You have successfully repaired the ${SERVICE_NAME} service on ${CURRENT_GIT_BRANCH} check status here: ${BUILD_URL}"
    }
  }
}
