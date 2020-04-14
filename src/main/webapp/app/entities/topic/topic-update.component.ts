import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { ITopic, Topic } from '@/shared/model/topic.model';
import TopicService from './topic.service';

const validations: any = {
  topic: {
    name: {},
    active: {},
    keyDeserializer: {},
    valueDeserializer: {},
    groupId: {},
    offset: {}
  }
};

@Component({
  validations
})
export default class TopicUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('topicService') private topicService: () => TopicService;
  public topic: ITopic = new Topic();
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.topicId) {
        vm.retrieveTopic(to.params.topicId);
      }
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.topic.id) {
      this.topicService()
        .update(this.topic)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Topic is updated with identifier ' + param.id;
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.topicService()
        .create(this.topic)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Topic is created with identifier ' + param.id;
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveTopic(topicId): void {
    this.topicService()
      .find(topicId)
      .then(res => {
        this.topic = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
