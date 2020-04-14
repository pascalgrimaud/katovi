<template>
    <div>
        <h2 id="page-heading">
            <span id="message-heading">Messages</span>
            <router-link :to="{name: 'MessageCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-message">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span >
                    Create a new Message
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
        <div class="alert alert-warning" v-if="!isFetching && messages && messages.length === 0">
            <span>No messages found</span>
        </div>
        <div class="table-responsive" v-if="messages && messages.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span>ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('date')"><span>Date</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'date'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('value')"><span>Value</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'value'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('topicName')"><span>Topic</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'topicName'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="message in messages"
                    :key="message.id">
                    <td>
                        <router-link :to="{name: 'MessageView', params: {messageId: message.id}}">{{message.id}}</router-link>
                    </td>
                    <td>{{message.date | formatDate}}</td>
                    <td>{{message.value}}</td>
                    <td>
                        <div v-if="message.topicId">
                            <router-link :to="{name: 'TopicView', params: {topicId: message.topicId}}">{{message.topicName}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'MessageView', params: {messageId: message.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline">View</span>
                            </router-link>
                            <router-link :to="{name: 'MessageEdit', params: {messageId: message.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(message)"
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
            <span slot="modal-title"><span id="katoviApp.message.delete.question">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-message-heading">Are you sure you want to delete this Message?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-message" v-on:click="removeMessage()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./message.component.ts">
</script>
