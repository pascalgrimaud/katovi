<template>
    <div>
        <h2 id="page-heading">
            <span id="topic-heading">Topics</span>
            <router-link :to="{name: 'TopicCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-topic">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span >
                    Create a new Topic
                </span>
            </router-link>
        </h2>
        <b-alert :show="dismissCountDown"
            dismissible
            :variant="alertType"
            @dismissed="dismissCountDown=0"
            @dismiss-count-down="countDownChanged">
            {{alertMessage}}
        </b-alert>
        <br/>
        <div class="alert alert-warning" v-if="!isFetching && topics && topics.length === 0">
            <span>No topics found</span>
        </div>
        <div class="table-responsive" v-if="topics && topics.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span>ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('name')"><span>Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('active')"><span>Active</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'active'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('keyDeserializer')"><span>Key Deserializer</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'keyDeserializer'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('valueDeserializer')"><span>Value Deserializer</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'valueDeserializer'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('groupId')"><span>Group Id</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'groupId'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('offset')"><span>Offset</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'offset'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="topic in topics"
                    :key="topic.id">
                    <td>
                        <router-link :to="{name: 'TopicView', params: {topicId: topic.id}}">{{topic.id}}</router-link>
                    </td>
                    <td>{{topic.name}}</td>
                    <td>{{topic.active}}</td>
                    <td>{{topic.keyDeserializer}}</td>
                    <td>{{topic.valueDeserializer}}</td>
                    <td>{{topic.groupId}}</td>
                    <td>{{topic.offset}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'TopicView', params: {topicId: topic.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline">View</span>
                            </router-link>
                            <router-link :to="{name: 'TopicEdit', params: {topicId: topic.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(topic)"
                                   variant="danger"
                                   class="btn btn-sm"
                                   v-b-modal.removeEntity>
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline">Delete</span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
                <infinite-loading
                    ref="infiniteLoading"
                    v-if="totalItems > itemsPerPage"
                    :identifier="infiniteId"
                    slot="append"
                    @infinite="loadMore"
                    force-use-infinite-wrapper=".el-table__body-wrapper"
                    :distance='20'>
                </infinite-loading>
            </table>
        </div>
        <b-modal ref="removeEntity" id="removeEntity" >
            <span slot="modal-title"><span id="katoviApp.topic.delete.question">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-topic-heading">Are you sure you want to delete this Topic?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-topic" v-on:click="removeTopic()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./topic.component.ts">
</script>
