export interface IMessage {
  id?: number;
  date?: Date;
  value?: string;
  topicName?: string;
  topicId?: number;
}

export class Message implements IMessage {
  constructor(public id?: number, public date?: Date, public value?: string, public topicName?: string, public topicId?: number) {}
}
