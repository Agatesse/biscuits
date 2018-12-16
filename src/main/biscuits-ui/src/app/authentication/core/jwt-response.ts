export class JwtResponse {
  private _accessToken: string;
  private _type: string;
  private _username: string;
  private _authorities: string[];
  private _id: number;


  get accessToken(): string {
    return this._accessToken;
  }

  get type(): string {
    return this._type;
  }

  get username(): string {
    return this._username;
  }

  get authorities(): string[] {
    return this._authorities;
  }

  get id(): number {
    return this._id;
  }
}
