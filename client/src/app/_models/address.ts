import { Gsec } from "./gsec";

export interface Address extends Gsec{
    addressLineOne: string;
    addressLineTwo: string;
    cityId: string;
}