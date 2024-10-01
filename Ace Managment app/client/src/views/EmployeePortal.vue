<script>
import aceService from "../services/ace-service";
import departmentService from "../services/department-service";

export default {
  data() {
    return {
      employee: null,
      employees: [],
      departments: [],
      selectedEmployeeId: null
    };
  },
  created() {
    this.loadEmployees();
    this.loadDepartments();
  },
  methods: {
    addEmployee() {
      const newEmployee = {
        name: this.name,
        hireDate: this.hireDate,
        leaveDate: null,
        phoneNumber: this.phoneNumber,
        hourlyRate: this.hourlyRate,
        departmentId: this.departmentId
      };

      aceService.addEmployee(newEmployee).then(response => {
        this.newEmployee = "";
        this.name = "";
        this.hireDate = "";
        this.leaveDate = null;
        this.phoneNumber = "";
        this.hourlyRate = "";
        this.departmentId = null;
        this.loadEmployees();
      });
    },

    selectEmployee(employeeId) {
      if (this.selectedEmployeeId === employeeId) {
        this.selectedEmployeeId = null;
      } else {
        this.selectedEmployeeId = employeeId;
      }
    },
    deleteEmployee(id) {
      aceService.deleteEmployee(id).then(response => {
        if (response.status === 204) {
          this.loadEmployees();
        }
      });
    },
    loadEmployees() {
      aceService.listEmployees().then(response => {
        this.employees = response.data;
      })
        .catch((error) => {
          console.error('Error listing employees', error);
        });
    },
    loadDepartments() {
      departmentService.listDepartments().then(response => {
        this.departments = response.data;
      })
        .catch(error => {
          console.error('Error listing departments', error);
        });
    },
    getDepartmentName(departmentId) {
      for (let i = 0; i < this.departments.length; i++) {
        if (this.departments[i].id === departmentId) {
          return this.departments[i].name;
        }
      }
      return "Unknown";
    },
    goToUpdatePage(id) {
      this.$router.push({ name: 'EmployeeDetails', params: { id } });
    }
  }
};
</script>

<template>
  <div>
    <h2>Employee Portal</h2>
    <table>
      <thead>
        <tr>
          <th>&nbsp;</th>
          <th>Id</th>
          <th>Name</th>
          <th>Department</th>
          <th>Phone Number</th>
          <th v-if="$store.state.userRoles.includes('ADMIN')">Hourly Rate</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="employee in employees" :key="employee.employeeId">
          <td>
            <input type="checkbox" :id="'selectEmployee' + employee.employeeId"
              @change="selectEmployee(employee.employeeId)" />
          </td>
          <td>{{ employee.employeeId }}</td>
          <td>{{ employee.name }}</td>
          <td>{{ employee.departmentId }}</td>
          <td>{{ employee.phoneNumber }}</td>
          <td v-if="$store.state.userRoles.includes('ADMIN')">
            {{ employee.hourlyRate.toFixed(2) }}
          </td>
        </tr>
      </tbody>
    </table>
    <section class="action-buttons" v-if="selectedEmployeeId && $store.state.userRoles.includes('ADMIN')">
      <button @click="deleteEmployee(selectedEmployeeId)">Delete Employee</button>
      <button @click="goToUpdatePage(selectedEmployeeId)">Update Employee</button>
    </section>
  </div>
  <section class="add-employee" v-if="$store.state.userRoles.includes('ADMIN')">
    <h2>Add New Employee</h2>
    <form class="form-styles" @submit.prevent="addEmployee">

      <label for="name">Name:</label>
      <input type="text" id="name" v-model="name" required>


      <label for="hireDate">Hire Date:</label>
      <input type="date" id="hireDate" v-model="hireDate" required>


      <label for="phoneNumber">Phone Number:</label>
      <input type="tel" id="phoneNumber" v-model="phoneNumber" required>


      <label for="hourlyRate">Hourly Rate:</label>
      <input type="number" id="hourlyRate" v-model="hourlyRate" step="0.25" required>

      <label for="department">Department:</label>
      <select id="department" v-model="departmentId" required>
        <option value="" disabled>Select a department</option>
        <option v-for="department in departments" :key="department.id" :value="department.id">
          {{ department.departmentName }}
        </option>
      </select>

      <button class="submit" type="submit">Add Employee</button>
    </form>
  </section>
</template>
  
<style scoped>
@import "open-props/style";

h2 {
  text-align: center;
  color: var(--red-8);
  font-family: Impact, 'Arial Narrow Bold', sans-serif;
}

table {
  width: 100%;
  margin: 0;
  padding: 0;
  border-collapse: collapse;
}

th,
td {
  padding: 10px;
  border: 1px solid var(--gray-4);
  text-align: left;
}

th {
  background-color: var(--gray-5);
  font-weight: bold;
}

h2 {
  color: var(--red-8);
  font-family: Impact, 'Arial Narrow Bold', sans-serif;
}

.form-styles {
  width: 350px;
  display: block;
  border: solid 2px var(--red-8);
  border-radius: 10px;
  padding: 10px;
  margin: 10px auto;
}

label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

input,
select {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
  font-size: 16px;
}

.action-buttons {
  display: flex;
  padding: 10px;
  gap: 10px;
}

button {
  background-color: var(--red-8);
  color: white;
  border: none;
  padding: 10px;
  margin-top: 10px;
  cursor: pointer;
}

button:hover {
  background-color: var(--red-9);
}

.add-employee {
  display: flex;
  flex-direction: column;
  width: 100%;
  align-items: center;
}

.submit {
  margin-left: 10px;
}

@media (max-width: 1024) {
  .layout {
    flex-direction: column;
    align-items: flex-start;
  }
}

@media (max-width: 425px) {
  h2 {
    font-size: 24px;
  }

  table {
    font-size: 14px;
  }

  th, td {
    padding: 5px;
  }

  .employee-info {
    display: block;
    padding: 10px;
    border: 1px solid var(--gray-4);
    margin-bottom: 10px;
  }

  .employee-info p {
    margin: 0;
    padding: 5px 0;
  }

  .form-styles {
    width: 90%;
    padding: 10px;
    margin: 10px auto;
  }

  .action-buttons {
    flex-direction: column;
    align-items: center;
  }

  button {
    padding: 10px;
    font-size: 14px;
  }
}
</style>