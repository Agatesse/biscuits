export class JwtResponse {
  private _accessToken: string;
  private _type: string;
  private _username: string;
  private _authorities: string[];


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
}
