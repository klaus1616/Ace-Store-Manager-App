<script>
import vendorService from '../services/vendor-service';
import departmentService from '../services/department-service';
import GoogleMaps from '../components/GoogleMaps.vue'

export default {
  components: {
    GoogleMaps    //trying to figure out how to add Google maps to display Vendor's address
  },
  data() {
    return {
      vendor: null,
      newVendor: {
        vendorName: "",
        contact: "",
        address: "",
        phoneNumber: "",
        departmentId: "",
      },
      vendors: [],
      departments: [],
      selectedVendorId: null
    };
  },
  created() {
    this.loadVendors();
    this.loadDepartments();

  },
  methods: {
    loadVendors() {
      vendorService.listVendors().then(response => {
        this.vendors = response.data;
      });
    },
    loadDepartments() {
      departmentService.listDepartments().then(response => {
        this.departments = response.data;
      });
    },
    selectVendor(vendorId) {

      this.selectedVendorId = vendorId;
      this.vendors.forEach(vendor => {
        if (vendor.vendorId === vendorId) {

          this.newVendor.vendorName = vendor.vendorName;
          this.newVendor.contact = vendor.contact;
          this.newVendor.address = vendor.address;
          this.newVendor.phoneNumber = vendor.phoneNumber;
          this.newVendor.departmentId = vendor.departmentId;
        }
      });
    },
    addVendor() {
      vendorService.addVendor(this.newVendor).then(response => {
        this.newVendor = {
          vendorName: "",
          contact: "",
          address: "",
          phoneNumber: "",
          departmentId: ""
        };
        this.loadVendors();
      });
    },
    updateVendor() {
      vendorService.updateVendor(this.selectedVendorId, this.newVendor).then(response => {
        this.selectedVendorId = null;
        this.loadVendors();
      });
    },
    deleteVendor(id) {
      vendorService.deleteVendor(id).then(response => {
        if (response.status === 204) {
          this.loadVendors();
        }
      });
    },
    goBackToVendor() {
      this.selectedVendorId = null;
      this.newVendor = {
        vendorName: "",
        contact: "",
        address: "",
        phoneNumber: "",
        departmentId: ""
      };
    },
  }
};
</script>

<template>
  <div>

    <h2>Outside Services</h2>

    <section v-for="vendor in vendors" :key="vendor.vendorId">
      <div class="vendor-card">
        <div class="vendor-information">
          <p><strong>Vendor Name:</strong> {{ vendor.vendorName }}</p>
          <p><strong>Contact:</strong> {{ vendor.contact }}</p>
          <p><strong>Address:</strong> {{ vendor.address }}</p>
          <p><strong>Call:</strong> {{ vendor.phoneNumber }}</p>
          <div v-if="$store.state.userRoles.includes('ADMIN')" class="action-buttons">
            <button @click="selectVendor(vendor.vendorId)">Update</button>
            <button @click="deleteVendor(vendor.vendorId)">Delete</button>
          </div>
        </div>
        <div class="vendor-img">
          <img v-if="vendor.vendorName.includes('M. B. Sharp LLC')" src="../images/sharpening.jpg"
            alt="Image of Knife Sharpening">
          <img v-if="vendor.vendorName.includes('Boom Window & Screen')" src="../images/screening.jpg"
            alt="Image of Window Screening">
        </div>
      </div>

    </section>

    <div v-if="selectedVendorId !== null">
      <h2>Update Vendor</h2>
      <form class="form-styles" @submit.prevent="updateVendor()">

        <label for="vendorName">Vendor Name:</label>
        <input v-model="newVendor.vendorName" placeholder="Vendor Name" required />

        <label for="contact">Contact:</label>
        <input v-model="newVendor.contact" placeholder="Contact" required />

        <label for="address">Address:</label>
        <input v-model="newVendor.address" placeholder="Address" required />

        <label for="phoneNumber">Phone Number:</label>
        <input v-model="newVendor.phoneNumber" placeholder="Phone Number" required />

        <select v-model="newVendor.departmentId" required>
          <label for="department">Department:</label>
          <option value="" disabled>Select a department</option>
          <option v-for="department in departments" :key="department.id" :value="department.id">
            {{ department.departmentName }}
          </option>
        </select>
        <div class="action-buttons">
          <button type="submit">Update Vendor</button>
          <button type="button" @click="goBackToVendor">Cancel</button>
        </div>
      </form>
    </div>

    <div v-if="selectedVendorId === null && $store.state.userRoles.includes('ADMIN')">
      <h2>Add New Vendor</h2>
      <form class="form-styles" @submit.prevent="addVendor()">

        <label for="vendorName">Vendor Name:</label>
        <input type="text" id="vendorName" v-model="newVendor.vendorName" required />

        <label for="contact">Contact:</label>
        <input type="text" id="contact" v-model="newVendor.contact" required />

        <label for="address">Address:</label>
        <input type="text" id="address" v-model="newVendor.address" required />

        <label for="phoneNumber">Phone Number:</label>
        <input type="text" id="phoneNumber" v-model="newVendor.phoneNumber" required />

        <label for="department">Department:</label>
        <select id="department" v-model="newVendor.departmentId" required>

          <label for="department">Department:</label>
          <option value="" disabled>Select a department</option>
          <option v-for="department in departments" :key="department.id" :value="department.id">
            {{ department.departmentName }}
          </option>
        </select>
        <div class="action-buttons">
          <button type="submit">Add Vendor</button>
          <button type="button" @click="goBackToVendor">Cancel</button>
        </div>
      </form>
    </div>
  </div>
</template>
  
<style scoped>
@import "open-props/style";

h2 {
  text-align: center;
  color: var(--red-8);
  font-family: Impact, 'Arial Narrow Bold', sans-serif;
}

.vendor-card {
  display: flex;
  justify-content: space-evenly;
  border: 2px solid var(--gray-4);
  border-radius: 10px;
  background-color: var(--gray-2);
  padding: 10px;
  margin-bottom: 10px;
}

.vendor-info {
  flex-grow: 1;
  padding: 10px;
}

.vendor-img img {
  display: flex;
  align-items: center;
  justify-content: center;
  border: 2px solid var(--gray-4);
  border-radius: 10px;
  max-width: 400px;
  height: auto;
}

.form-styles {
  width: 350px;
  display: block;
  border: solid 2px var(--red-8);
  border-radius: 10px;
  padding: 10px;
  margin: 10px auto;
}

input,
select {
  width: 100%;
  padding: 5px;
  margin: 10px 0;
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
  cursor: pointer;
}

button:hover {
  background-color: var(--red-9);
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

  .vendor-card {
    padding: 5px;
  }

  .vendor-info {
    font-size: 14px; 
  }

  .vendor-img {
    margin-top: 5px; 
  }

  .vendor-img img {
    max-width: 100%; 
    height: auto;
  }

  .action-buttons {
    flex-direction: column;
  }

}
</style>