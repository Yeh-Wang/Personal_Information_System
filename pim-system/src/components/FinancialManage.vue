<template>
  <el-row style="height: 15px">
    <el-col :span="10"><div class="grid-content ep-bg-purple"/></el-col>
    <el-col :span="13"><div class="grid-content ep-bg-purple"/></el-col>
    <el-col :span="1">
      <el-tooltip
          effect="dark"
          content="Add a Financial Information"
          placement="left"
      >
      <el-button circle @click="centerAddDialogVisible=true" color="#626aef" icon="Plus"/>
      </el-tooltip>
    </el-col>
  </el-row>

  <el-table :data="tableData.finInfo" :stripe="true" :border="true" height="550" style="width: 100%">
    <el-table-column prop="finTime" :formatter="dateFormat" label="Financial Date Time"/>
    <el-table-column prop="finUserName" label="User Name"/>
    <el-table-column prop="finProgram" label="Event"/>
    <el-table-column prop="finIncome" label="Income(RMB)"/>
    <el-table-column prop="finOutlay" label="Outlay(RMB)" />
    <el-table-column prop="finAmount" label="Amount(RMB)" />
    <el-table-column prop="finAddTime" :formatter="dateFormat" label="Add Time"/>
    <el-table-column fixed="right" label="Operations">
      <template #default="scope">
        <el-button link type="primary" size="small" @click="handleEdit(scope.$index,scope.row)"
        >Edit</el-button
        >
        <el-button link type="danger" size="small" @click="handleDelete(scope.$index,scope.row)">Delete</el-button>
      </template>
    </el-table-column>
  </el-table>
<!--Add a financial information-->
  <el-dialog
      v-model="centerAddDialogVisible"
      title="Add a Financial Information"
      width="30%"
      align-center
  >
    <el-form :model="addForm" label-width="120px">
      <el-form-item label="User Name">
        <el-input v-model="addForm.finUserName"/>
      </el-form-item>
      <el-form-item label="Event">
        <el-input v-model="addForm.finProgram"/>
      </el-form-item>
      <el-form-item label="Financial Date">
        <el-col :span="15">
          <el-date-picker
              v-model="addForm.finTime"
              type="datetime"
              placeholder="Pick a date"
              style="width: 100%"
              value-format="YYYY-MM-DD HH:mm:ss"
          />
        </el-col>
      </el-form-item>
      <el-form-item label="Income">
        <el-input v-model="addForm.finIncome"/>
      </el-form-item>
      <el-form-item label="Outlay">
        <el-input v-model="addForm.finOutlay"/>
      </el-form-item>
      <el-form-item label="Add Date">
        <el-col :span="15">
          <el-date-picker
              v-model="addForm.finAddTime"
              type="datetime"
              placeholder="Pick a date"
              style="width: 100%"
              value-format="YYYY-MM-DD HH:mm:ss"
          />
        </el-col>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmitAdd">Submit</el-button>
        <el-button @click="addClose">Cancel</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>

<!--  update a Financial Information-->
  <el-dialog
      v-model="centerUpdateDialogVisible"
      title="Update a Financial Information"
      width="30%"
      align-center
  >
    <el-form :model="updateForm" label-width="120px">
      <el-form-item label="User Name">
        <el-input v-model="updateForm.finUserName"/>
      </el-form-item>
      <el-form-item label="Event">
        <el-input v-model="updateForm.finProgram"/>
      </el-form-item>
      <el-form-item label="Financial Date">
        <el-col :span="15">
          <el-date-picker
              v-model="updateForm.finTime"
              type="datetime"
              placeholder="Pick a date"
              style="width: 100%"
              value-format="YYYY-MM-DD HH:mm:ss"
          />
        </el-col>
      </el-form-item>
      <el-form-item label="Income">
        <el-input v-model="updateForm.finIncome"/>
      </el-form-item>
      <el-form-item label="Outlay">
        <el-input v-model="updateForm.finOutlay"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmitUpdate">Submit</el-button>
        <el-button @click="updateClose">Cancel</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>

  <!--    确认框-->
  <el-dialog
      v-model="dialogVisible"
      title="Tips"
      width="30%"
  >
    <span>Are you sure?</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="deleteMakeSure">
          Confirm
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts">
import axios from "axios";
import moment from "moment";
import {defineComponent, reactive, ref} from "vue";
import {ElMessage} from "element-plus";

export default defineComponent({
  name: "FinancialManage",

  setup(){
    interface finInfo{
      perId:string;
      finId:number;
      finUserName:string;
      finIncome:number;
      finOutlay:number;
      finProgram:string;
      finTime:string;
      finAmount:number;
      finAddTime:string;
    }
    const tableData = reactive({
      finInfo:[{
        perId:"1",
        finId:1,
        finUserName:" ",
        finIncome:0.00,
        finOutlay:0.00,
        finProgram:" ",
        finTime:" ",
        finAmount:0.00,
        finAddTime:" ",
      }]
    })
    const dateFormat = (row:any,column:any) =>{  //时间过滤器
      const dateTime = row[column.property]
      if(dateTime === " "){
        return " "
      }
      return moment(dateTime).utc().format('YYYY-MM-DD HH:mm:ss')
    }
    //Add a financial information
    const centerAddDialogVisible =ref(false)
    const addForm = ref({
      perId:"1",
      finId:1,
      finUserName:" ",
      finIncome:0.00,
      finOutlay:0.00,
      finProgram:" ",
      finTime:" ",
      finAmount:0.00,
      finAddTime:" ",
    })
    const addFinancial = () =>{
    }
    const onSubmitAdd = () =>{
      console.log(addForm.value)
      addForm.value.finAmount=addForm.value.finIncome-addForm.value.finOutlay
      axios.post("http://localhost:9090/financial-management-entity/addFinancial",addForm.value).then(res=>{
            if (res.data === 1) {
              ElMessage({
                message: "Add successful",
                type: 'success'
              })
              centerAddDialogVisible.value=false
            } else {
              ElMessage.error("Failed to add. Please add again")
            }
      })
      addForm.value.perId="1";addForm.value.finId=1;addForm.value.finUserName=" ";addForm.value.finIncome=0.00;addForm.value.finOutlay=0.00;
      addForm.value.finProgram=" ";addForm.value.finTime=" ";addForm.value.finAmount=0.00;addForm.value.finAddTime=" ";
    }
    const addClose = () =>{
      addForm.value.perId="1";addForm.value.finId=1;addForm.value.finUserName=" ";addForm.value.finIncome=0.00;addForm.value.finOutlay=0.00;
      addForm.value.finProgram=" ";addForm.value.finTime=" ";addForm.value.finAmount=0.00;addForm.value.finAddTime=" ";
      centerAddDialogVisible.value=false
    }
    //Update a financial information
    const updateForm = ref({
      perId:"1",
      finId:1,
      finUserName:" ",
      finIncome:0.00,
      finOutlay:0.00,
      finProgram:" ",
      finTime:" ",
      finAmount:0.00,
      finAddTime:" ",
    })
    const centerUpdateDialogVisible = ref(false)
    const handleEdit = (index:number, row:finInfo) => { //Edit Information
      updateForm.value=row;
      centerUpdateDialogVisible.value=true
    }
    const onSubmitUpdate = () => {
      updateForm.value.finAmount=updateForm.value.finIncome-updateForm.value.finOutlay
      axios.post("http://localhost:9090/financial-management-entity/updateFinancial",updateForm.value).then(res=>{
          if (res.data === 1) {
            ElMessage({
              message: "Update successful",
              type: 'success'
            })
            centerUpdateDialogVisible.value=false
          } else {
            ElMessage.error("There is no Financial Information")
          }
      })
    }
    const updateClose = () => {
      centerUpdateDialogVisible.value=false
    }
    //Delete a Financial Information
    const deleteId = ref(0)
    const dialogVisible = ref(false)
    const handleDelete = (index:number, row:finInfo) => { //Delete Information
      dialogVisible.value=true
      deleteId.value=row.finId
    }
    const deleteMakeSure = () => {
      axios.post("http://localhost:9090/financial-management-entity/deleteFinancial/"+deleteId.value).then(res=>{
        if (res.data === 1) {
          ElMessage({
            message: "Delete successful",
            type: 'success'
          })
          dialogVisible.value=false
        } else {
          ElMessage.error("There is no Financial Information")
        }
      })
    }
    return {
      tableData,
      handleEdit,
      updateForm,
      dateFormat,
      handleDelete,
      dialogVisible,
      deleteMakeSure,
      centerUpdateDialogVisible,
      onSubmitUpdate,
      updateClose,
      centerAddDialogVisible,
      addForm,
      addFinancial,
      onSubmitAdd,
      addClose
    }
  },

  mounted() {
    axios.get("http://localhost:9090/financial-management-entity/getAllFinancialInfo").then(res=>{
      this.tableData.finInfo=res.data
    })
  }
})
</script>

<style scoped>

</style>