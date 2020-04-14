<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="katoviApp.topic.home.createOrEditLabel">Create or edit a Topic</h2>
                <div>
                    <div class="form-group" v-if="topic.id">
                        <label for="id">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="topic.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="topic-name">Name</label>
                        <input type="text" class="form-control" name="name" id="topic-name"
                            :class="{'valid': !$v.topic.name.$invalid, 'invalid': $v.topic.name.$invalid }" v-model="$v.topic.name.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="topic-active">Active</label>
                        <input type="checkbox" class="form-check" name="active" id="topic-active"
                            :class="{'valid': !$v.topic.active.$invalid, 'invalid': $v.topic.active.$invalid }" v-model="$v.topic.active.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="topic-keyDeserializer">Key Deserializer</label>
                        <input type="text" class="form-control" name="keyDeserializer" id="topic-keyDeserializer"
                            :class="{'valid': !$v.topic.keyDeserializer.$invalid, 'invalid': $v.topic.keyDeserializer.$invalid }" v-model="$v.topic.keyDeserializer.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="topic-valueDeserializer">Value Deserializer</label>
                        <input type="text" class="form-control" name="valueDeserializer" id="topic-valueDeserializer"
                            :class="{'valid': !$v.topic.valueDeserializer.$invalid, 'invalid': $v.topic.valueDeserializer.$invalid }" v-model="$v.topic.valueDeserializer.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="topic-groupId">Group Id</label>
                        <input type="text" class="form-control" name="groupId" id="topic-groupId"
                            :class="{'valid': !$v.topic.groupId.$invalid, 'invalid': $v.topic.groupId.$invalid }" v-model="$v.topic.groupId.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="topic-offset">Offset</label>
                        <input type="text" class="form-control" name="offset" id="topic-offset"
                            :class="{'valid': !$v.topic.offset.$invalid, 'invalid': $v.topic.offset.$invalid }" v-model="$v.topic.offset.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.topic.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./topic-update.component.ts">
</script>
