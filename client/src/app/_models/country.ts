import { Gsec } from "./gsec";

export interface Country extends Gsec{
    countryCode: string;
    name: string;
}