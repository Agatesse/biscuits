export class User {
  private _id: number;
  private _username: string;
  private _email: string;
  private _password: string;
  private _role: string;

  constructor() {
  }

  public get id(): number {
    return this._id;
  }
  public set id(value: number) {
    this._id = value;
  }

  public get username(): string {
    return this._username;
  }
  public set username(value: string) {
    this._username = value;
  }

  public get email(): string {
    return this._email;
  }
  public set email(value: string) {
    this._email = value;
  }

  public get password(): string {
    return this._password;
  }
  public set password(value: string) {
    this._password = value;
  }

  public get role(): string {
    return this._role;
  }
  public set role(value: string) {
    this._role = value;
  }

}
