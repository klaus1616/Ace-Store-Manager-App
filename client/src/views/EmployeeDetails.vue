<script>

import aceService from '../services/ace-service';
import departmentService from '../services/department-service';

export default {
  data() {
    return {
      employee: null,
      departments: []
    };
  },
  created() {
    this.loadEmployeeDetails();
    this.loadDepartments()
  },
  methods: {
    loadEmployeeDetails() {
      const employeeId = this.$route.params.id;
      aceService.getEmployee(employeeId).then(response => {
        this.employee = response.data;
      }).catch(error => {
        console.error('Error listing employee details:', error);
      })
    },
    updateEmployee() {
      const employeeId = this.$route.params.id;
      aceService.updateEmployee(employeeId, this.employee).then(response => {
        this.$router.push('/employeePortal');
      }).catch(error => {
        console.error('Error updating employee:', error);
      });
    },
    cancelUpdate() {
      this.$router.push('/employeePortal');
    },
    loadDepartments() {
      departmentService.listDepartments().then(response => {
        this.departments = response.data;
      }).catch(error => {
        console.error('Error listing departments:', error);
      });
    }
  }
}
</script>

<template>
  <div>
    <h2>Employee Details</h2>
    <section class="update-employee">
      <form @submit.prevent="updateEmployee">
        <div>
          <label for="employeeId">Employee Id:</label>
          <input type="number" id="employeeId" v-model="employee.employeeId" required />
        </div>
        <div>
          <label for="name">Name:</label>
          <input type="text" id="name" v-model="employee.name" required />
        </div>
        <div>
          <label for="hireDate">Hire Date:</label>
          <input type="date" id="hireDate" v-model="employee.hireDate" required />
        </div>
        <div>
          <label for="leaveDate">Leave Date:</label>
          <input type="date" id="leaveDate" v-model="employee.leaveDate" />
        </div>
        <div>
          <label for="phoneNumber">Phone Number:</label>
          <input type="text" id="phoneNumber" v-model="employee.phoneNumber" required />
        </div>
        <div>
          <label for="hourlyRate">Hourly Rate:</label>
          <input type="number" id="hourlyRate" v-model="employee.hourlyRate" step="0.25" />
        </div>
        <div>
          <label for="departmentId">Department:</label>
          <select id="departmentId" v-model="employee.departmentId" required>
            <option value="" disabled>Select a department</option>
            <option v-for="department in departments" :key="department.id" :value="department.id">
              {{ department.departmentName }}
            </option>
          </select>
        </div>
        <section class="action-buttons">
          <button type="submit">Update Employee</button>
          <button @click="cancelUpdate">Cancel</button>
        </section>
      </form>
    </section>
  </div>
</template>

<style scoped>
@import "open-props/style";

h2 {
  text-align: center;
  color: var(--red-8);
  font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
}

.form-group {
  padding: 5px;
  margin: 5px;
}

label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

input,
select {
  width: 350px;
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

.update-employee {
  display: flex;
  flex-direction: column;
  align-items: center;
}

@media (max-width: 1024) {
  .layout {
    flex-direction: column;
    align-items: flex-start;
  }

  input, select {
    width: 100%
  }
}

@media (max-width: 425px) {
  h2 {
    font-size: 28px;
  }

  button {
    padding: 5px;
    margin: 5px;
  }

  .action-buttons {
    flex-direction: column;
  }
}
</style>