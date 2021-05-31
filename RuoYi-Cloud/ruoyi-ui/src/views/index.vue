<template>
  <div class="app-container home">
    <el-col :xs="24" :sm="24" :md="12" :lg="8">
      <el-card class="update-log">
        <div slot="header" class="clearfix">
          <span>技术说明</span>
        </div>
        <el-collapse accordion>
          <el-collapse-item title="后台技术">
            <ol>
              <li>Spring Boot 2.3.x</li>
              <li> Spring Cloud Hoxton.SR9</li>
              <li> Spring Framework 5.2.x</li>
              <li> Spring Security 5.2.x</li>
              <li> Apache MyBatis 3.5.x</li>
              <li> Hibernate Validation 6.0.x</li>
              <li> Alibaba Druid 1.2.x</li>
            </ol>
          </el-collapse-item>
          <el-collapse-item title="前端技术">
            <ol>
              <li>Vue 2.6.x</li>
              <li>VAxios 0.21.0</li>
              <li>VElement 2.14.x</li>
            </ol>
          </el-collapse-item>
        </el-collapse>
      </el-card>
    </el-col>
    <el-col :xs="24" :sm="24" :md="12" :lg="8">
      <el-card class="update-log">
        <div slot="header" class="clearfix">
          <span>更新日志</span>
        </div>
        <el-collapse accordion>
          <el-collapse-item title="v2.5.5 - 2021-05-31">
            <ol>
              <li>新增手机号码登录功能</li>
            </ol>
          </el-collapse-item>
          <el-collapse-item title="v2.5.4 - 2021-05-30">
            <ol>
              <li>新增基础数据管理代码</li>
            </ol>
          </el-collapse-item>
          <el-collapse-item title="v2.5.3 - 2021-05-29">
            <ol>
              <li>增加多租户帮助类：MyTenantLineHandler.java</li>
              <li>同步2021-05-27版本</li>
            </ol>
          </el-collapse-item>
          <el-collapse-item title="v2.5.2 - 2021-05-18">
            <ol>
              <li>增加Canal的支持</li>
              <li>同步2021-05-11版本</li>
            </ol>
          </el-collapse-item>
          <el-collapse-item title="v2.5.1 - 2021-04-24">
            <ol>
              <li>增加MybatisPlus支持</li>
              <li>增加lombok支持</li>
            </ol>
          </el-collapse-item>
          <el-collapse-item title="v2.5.0 - 2021-04-23">
            <ol>
              <li>初始化</li>
            </ol>
          </el-collapse-item>
        </el-collapse>
      </el-card>
    </el-col>
  </div>
</template>

<script>
  export default {
    name: 'index',
    data() {
      return {
        // 版本号
        version: '2.5.0'
      }
    },
    methods: {
      goTarget(href) {
        window.open(href, '_blank')
      }
    }
  }
</script>

<style scoped lang="scss">
  .home {

  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }

  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }

  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family:

  "open sans"
  ,
  "Helvetica Neue"
  ,
  Helvetica, Arial, sans-serif

  ;
  font-size:

  13
  px

  ;
  color: #676a6c

  ;
  overflow-x: hidden

  ;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

  b {
    font-weight: 700;
  }

  }

  .update-log {

  ol {
    display: block;
    list-style-type: decimal;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0;
    margin-inline-end: 0;
    padding-inline-start: 40px;
  }

  }
  }
</style>

