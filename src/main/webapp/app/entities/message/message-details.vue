<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="message">
                <h2 class="jh-entity-heading"><span>Message</span> {{message.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span>Date</span>
                    </dt>
                    <dd>
                        <span>{{message.date | formatDate}}</span>
                    </dd>
                    <dt>
                        <span>Value</span>
                    </dt>
                    <dd>
                        <span>{{message.value}}</span>
                    </dd>
                    <dt>
                        <span>Topic</span>
                    </dt>
                    <dd>
                        <div v-if="message.topicId">
                            <router-link :to="{name: 'TopicView', params: {topicId: message.topicId}}">{{message.topicName}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
                </button>
                <router-link v-if="message.id" :to="{name: 'MessageEdit', params: {messageId: message.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./message-details.component.ts">
</script>
