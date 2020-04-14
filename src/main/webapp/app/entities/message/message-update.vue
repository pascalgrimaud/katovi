<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="katoviApp.message.home.createOrEditLabel">Create or edit a Message</h2>
                <div>
                    <div class="form-group" v-if="message.id">
                        <label for="id">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="message.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="message-date">Date</label>
                        <div class="d-flex">
                            <input id="message-date" type="datetime-local" class="form-control" name="date" :class="{'valid': !$v.message.date.$invalid, 'invalid': $v.message.date.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.message.date.$model)"
                            @change="updateInstantField('date', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="message-value">Value</label>
                        <input type="text" class="form-control" name="value" id="message-value"
                            :class="{'valid': !$v.message.value.$invalid, 'invalid': $v.message.value.$invalid }" v-model="$v.message.value.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="message-topic">Topic</label>
                        <select class="form-control" id="message-topic" name="topic" v-model="$v.message.topicId.$model" required>
                            <option v-if="!message.topicId" v-bind:value="null" selected></option>
                            <option v-bind:value="topicOption.id" v-for="topicOption in topics" :key="topicOption.id">{{topicOption.name}}</option>
                        </select>
                    </div>
                    <div v-if="$v.message.topicId.$anyDirty && $v.message.topicId.$invalid">
                        <small class="form-text text-danger" v-if="!$v.message.topicId.required">
                            This field is required.
                        </small>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.message.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./message-update.component.ts">
</script>
