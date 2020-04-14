export interface ITopic {
  id?: number;
  name?: string;
  active?: boolean;
  keyDeserializer?: string;
  valueDeserializer?: string;
  groupId?: string;
  offset?: string;
}

export class Topic implements ITopic {
  constructor(
    public id?: number,
    public name?: string,
    public active?: boolean,
    public keyDeserializer?: string,
    public valueDeserializer?: string,
    public groupId?: string,
    public offset?: string
  ) {
    this.active = this.active || false;
  }
}
