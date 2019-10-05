import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Echarts from '@/components/Echarts'
import Test from '@/components/Test'
import IndexPage from "@/components/IndexPage"
import Table1 from "@/components/Table"
import UserManagement from "@/components/UserManagement/Index"
import UserModifiy from "@/components/UserManagement/modifiy"
import AddUser from "@/components/UserManagement/AddUser"
import DeptMangement from "@/components/DeptManagement/Index"
import DeptModifiy from "@/components/DeptManagement/Modifiy"
import AddDept from "@/components/DeptManagement/AddDept"
import ShowLog from "@/components/Performance/ShowLog"
import Project from "@/components/Project/Index"
import PojModifiy from "@/components/Project/Modifiy"
import AddPoj from "@/components/Project/AddPoj"
import WorkLog from "@/components/WorkLog/Index"
import LogModifiy from "@/components/WorkLog/Modifiy"
import AddLog from "@/components/WorkLog/AddLog"
import Performance from "@/components/Performance/Index"
import AddScore from "@/components/Performance/AddScore"
import ShowScore from "@/components/Performance/ShowScore"

Vue.use(Router)

export default new Router({
  routes: [

    {
      path: "/Echarts",
      name: "Echarts",
      component: Echarts
    },
    {
      path: '/',
      name: 'indexpage',
      component: IndexPage
    },
    {
      path: "/hello",
      name: "HelloWord",
      component: HelloWorld
    },
    {
      path: "/table1",
      name: "Table1",
      component: Table1
    },
    {
      path: "/test",
      name: "Test",
      component: Test
    },
    {
      path: "/UserManagement",
      name: "UserManagement",
      component: UserManagement
    },
    {
      path: "/UserModifiy",
      name: "UserModifiy",
      component: UserModifiy
    },
    {
      path: "/AddUser",
      name: "AddUser",
      component: AddUser
    },
    {
      path: "/Dept",
      name: "DeptMangement",
      component: DeptMangement
    },
    {
      path: "/DeptModifiy",
      name: "DeptModifiy",
      component: DeptModifiy
    },{
      path: "/AddDept",
      name: "AddDept",
      component: AddDept
    },
    {
      path: "/ShowLog",
      name: "ShowLog",
      component: ShowLog
    },
    {
      path: "/Project",
      name: "Project",
      component: Project
    },
    {
      path: "/PojModifiy",
      name: "PojModifiy",
      component: PojModifiy
    },
    {
      path: "/AddPoj",
      name: "AddPoj",
      component: AddPoj
    },
    {
      path: "/WorkLog",
      name: "WorkLog",
      component: WorkLog
    },
    {
      path: "/LogModifiy",
      name: "LogModifiy",
      component: LogModifiy
    },
    {
      path: "/AddLog",
      name: "AddLog",
      component: AddLog
    },
    {
      path: "/Performance",
      name: "Performance",
      component: Performance
    },
    {
      path: "/AddScore",
      name: "AddScore",
      component: AddScore
    },
    {
      path: "/ShowScore",
      name: "ShowScore",
      component: ShowScore
    }
  ]
})
