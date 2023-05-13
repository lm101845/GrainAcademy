<!--
 * @Author liming
 * @Date 2023/5/13 15:13
-->
<template>
  <div class="app-container">
    讲师列表

    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="teacherQuery.name" placeholder="讲师名"/>
      </el-form-item>

      <el-form-item>
        <el-select v-model="teacherQuery.level" clearable placeholder="讲师头衔">
          <el-option :value="1" label="高级讲师"/>
          <el-option :value="2" label="首席讲师"/>
        </el-select>
      </el-form-item>

      <el-form-item label="添加时间">
        <el-date-picker
          v-model="teacherQuery.begin"
          type="datetime"
          placeholder="选择开始时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="teacherQuery.end"
          type="datetime"
          placeholder="选择截止时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>

      <el-button type="primary" icon="el-icon-search" @click="getList()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>

    <!-- 表格 -->
    <el-table
      :data="list"
      border
      fit
      highlight-current-row>

      <el-table-column
        label="序号"
        width="70"
        align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
  <!--在element-ui中，slot-scope="scope"是用来定义插槽作用域的。它表示插槽内部的内容可以访问父组件中的数据，
       并且可以通过scope对象来访问这些数据。具体来说，scope对象可以包含以下属性：
    - scope.row：当前行的数据
    - scope.column：当前列的数据
    - scope.$index：当前行的索引
    - scope.$parent：父组件的数据
  -->
        </template>
      </el-table-column>

      <el-table-column prop="name" label="名称" width="80" />

      <el-table-column label="头衔" width="80">
        <template slot-scope="scope">
          {{ scope.row.level===1?'高级讲师':'首席讲师' }}
        </template>
      </el-table-column>

      <el-table-column prop="intro" label="资历" />

      <el-table-column prop="gmtCreate" label="添加时间" width="160"/>

      <el-table-column prop="sort" label="排序" width="60" />

      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <!--隐藏路由-->
          <router-link :to="'/teacher/save/'+scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">修改</el-button>
          </router-link>
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
      :current-page="page"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="getList"
    />

  </div>
</template>

<script>
// 引入调用teacher.js文件
import teacher from '@/api/edu/teacher'
import axios from 'axios'
export default {
  // 写核心代码位置
  name: 'List',
  data() {
    return {
      list: null, //查询之后接口返回集合
      page: 1, // 当前页
      limit: 10, // 每页记录数
      total: 0,  // 总记录数
      teacherQuery: {}, //条件封装对象
    }
  },
  created() { // 页面渲染之前执行，里面调用methods里面的方法
    this.getList()
  },
  methods: { // 创建具体的方法，调用teacher.js里面的方法
    //讲师列表的方法
    getList(page = 1){
     this.page = page
     teacher.getTeacherListPage(this.page,this.limit,this.teacherQuery)
       .then(response=>{
         console.log(response,'response')
         this.list = response.data.rows
         this.total = response.data.total
       })  //请求成功
       // .catch(error => {
       //   console.log(error,'error')
       // })
      //不用写catch，request已经封装了
    },
    //清空(1.清空表单输入项数据；2.查询所有讲师数据)
    resetData(){
      this.teacherQuery = {}
      this.getList();
    },
    //删除讲师的方法
    removeDataById(id){
      console.log(id)
      this.$confirm('此操作将永久删除讲师记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {  //点击确定，执行then方法
        //调用删除的方法
        teacher.deleteTeacherId(id)
          .then(response =>{//删除成功
            //提示信息
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            //回到列表页面
            this.getList()
          })
      }) //点击取消，执行catch方法
    }
  }
}
</script>

<style scoped>

</style>
