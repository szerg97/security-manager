import { Gsec } from "./gsec";

export interface Transaction extends Gsec{
    securityName: string;
    denomination: number;
    netValue: number;
    yield: number;
    referenceYield: number;
    porfolioId?: string;
    issuerId: string;
    securityId: string;
}