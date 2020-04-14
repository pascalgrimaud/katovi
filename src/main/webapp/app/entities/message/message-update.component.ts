import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';
import format from 'date-fns/format';
import parse from 'date-fns/parse';
import parseISO from 'date-fns/parseISO';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import TopicService from '../topic/topic.service';
import { ITopic } from '@/shared/model/topic.model';

import AlertService from '@/shared/alert/alert.service';
import { IMessage, Message } from '@/shared/model/message.model';
import MessageService from './message.service';

const validations: any = {
  message: {
    date: {},
    value: {},
    topicId: {
      required
    }
  }
};

@Component({
  validations
})
export default class MessageUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('messageService') private messageService: () => MessageService;
  public message: IMessage = new Message();

  @Inject('topicService') private topicService: () => TopicService;

  public topics: ITopic[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.messageId) {
        vm.retrieveMessage(to.params.messageId);
      }
      vm.initRelationships();
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.message.id) {
      this.messageService()
        .update(this.message)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Message is updated with identifier ' + param.id;
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.messageService()
        .create(this.message)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Message is created with identifier ' + param.id;
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public convertDateTimeFromServer(date: Date): string {
    if (date) {
      return format(date, DATE_TIME_LONG_FORMAT);
    }
    return null;
  }

  public updateInstantField(field, event) {
    if (event.target.value) {
      this.message[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.message[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.message[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.message[field] = null;
    }
  }

  public retrieveMessage(messageId): void {
    this.messageService()
      .find(messageId)
      .then(res => {
        res.date = new Date(res.date);
        this.message = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.topicService()
      .retrieve()
      .then(res => {
        this.topics = res.data;
      });
  }
}
