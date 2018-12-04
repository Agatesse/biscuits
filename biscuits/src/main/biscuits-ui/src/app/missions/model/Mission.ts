export class Mission {
  private _id: number;
  private _action: string;
  private _imageURL: string;
  private _isDone: boolean;
  private _biscuitsToEarn: number;

  constructor(id: number, action: string, imageURL: string, isDone: boolean, biscuitsToEarn: number) {
    this._id = id;
    this._action = action;
    this._imageURL = imageURL;
    this._isDone = isDone;
    this._biscuitsToEarn = biscuitsToEarn;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get action(): string {
    return this._action;
  }

  set action(value: string) {
    this._action = value;
  }

  get imageURL(): string {
    return this._imageURL;
  }

  set imageURL(value: string) {
    this._imageURL = value;
  }

  get isDone(): boolean {
    return this._isDone;
  }

  set isDone(value: boolean) {
    this._isDone = value;
  }

  get biscuitsToEarn(): number {
    return this._biscuitsToEarn;
  }

  set biscuitsToEarn(value: number) {
    this._biscuitsToEarn = value;
  }
}
