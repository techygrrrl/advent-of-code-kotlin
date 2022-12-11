object Day03 {

    private fun getPriority(char: Char): Int =
        (('a'..'z') + ('A'..'Z')).indexOf(char) + 1

    private fun String.letters(): List<Char> =
        this.split("")
            .filter { it.isNotBlank() }
            .map { it[0] }

    fun solveRucksackPart1(): Int =
        realInput.split("\n")
            .sumOf { ruckSack ->
                val middleIndex = ruckSack.length / 2
                val leftCompartment = ruckSack.substring(0, middleIndex).letters()
                val rightCompartment = ruckSack.substring(middleIndex, ruckSack.length).letters()

                val commonChar = leftCompartment.find { rightCompartment.indexOf(it) > -1 }!!

                getPriority(commonChar)
            }


    /**
     * Part 2 b) Solving part 2 with a dynamic group size
     */
    fun solveRucksackPart2WithGroupSize(groupSize: Int): Int {
        return realInput
            .split("\n")
            .map { it.letters() }
            .chunked(groupSize)
            .sumOf { group ->
                val sets = group.map { it.toSet() }
                val commonChar = sets
                    .reduce { a, b -> a.intersect(b) }
                    .first()

                getPriority(commonChar)
            }
    }

    /**
     * Part 2 a) Solving part 2 with a hardcoded group size of 3
     */
    fun solveRucksackPart2Original(): Int =
        realInput
            .split("\n")
            .map { it.letters() }
            .chunked(3)
            .sumOf { group ->
                val elf1 = group[0]
                val elf2 = group[1]
                val elf3 = group[2]

                val badge = elf1.find { char ->
                    elf2.indexOf(char) > -1 && elf3.indexOf(char) > -1
                }!!

                getPriority(badge)
            }

    private val realInput = """
        MVWpzTTrTFNNLtssjV
        hRJncnJCnhPCnBSbCQRhhQRPFHmsbHLzbLNHsjNNFmGGGsGF
        lSBQJBBBBcnccnQvBnPQznfrgwlrTZfDwTfWqrrpgMpw
        sRPgrzSgrSbfTrgspBPsDWWTmdnvdZWZwTmwvdmd
        tVGpCGqCGjlHcNGVNHZDmnZMWdWMWCdZDvnZ
        HqpQptLlclLGtlpcjHNhQqfRhrSBrrbfbrSRrsPfBSgg
        JpjLbQbFPBjDBBJLWltglfBfhhlcctht
        vNFmsdFsnmzGtWvgzhzc
        rqwRCCqmCTqHCnqRNTNFsJVMQSjLRbbVVbjQVLbLSV
        mLNNCNDwBwDnmCwnJwLRvdlqZclRccsgvcZndc
        QWMtVWbpVlgHHcgMHs
        VsTVWhThsVQWzjtQPpVWjWbpwJNCJDCzSNNCCCSfmfBCSGLL
        NbSfHnwDvwwfHwwQsHbWPgrsZsZjRPLRgLWhWP
        lmMlTGFzVmzqjGLLZWWGhrCh
        qFclMprqmrvbcnwDQtNQ
        tWQZFvvtWQWbqQQggZZLvpLrpzDrmGDmmDHPzPzHrfnHTG
        NMlhlTMccTCVBlRNHzJnzDDmnJmnGGBf
        NMMSSSSSlNVMdjdNSNNhFwTbvbLqjbtLwWQwZqgg
        jPwcJwRmmhJpbhNJVgDbrHzzzQzzBQHg
        tdZqlCnnnlvZCqlnlCSqZdFCHGDBgzsDzssBtHGLQtrHsssL
        TZZFSdrdlZMFZRMwMPmNcwNmwm
        nsdhzmDBGQWQPvJPjbbW
        gCgBqCNpMHTwgwqMPRJJTtWjbFRJFJvP
        ZlCwBrwgmzhGzDrd
        sTBHfcnBTnqHRvqPgFFbLtrQTPLjjm
        GWzpwSJSpbwbNNGJPQrrtrrrrgzLtjzm
        NNSlCCdplWwplCwSndnssdZfqVbHvfqc
        rrfHgqnlllRrDgrCbQfszMPtmzPQzFsFMQ
        JJLGVGjcwVcPQNNNtRPmLM
        WThVJJWJBdGwBpBTqDrSRCCggShqbSCb
        TbCqzqzmbCffzDbHRddLbdRFRS
        ZmvZJPjPwwWNZJGtWSDRRDFWSLhhhMVVWL
        ZwplGwmptNjZnjvnGGPjJlZppTrTsCczfggBgfgfggCsBrqr
        gMBBbfBbBMfnMsvRvWJhDsQW
        ZZqHLzczjjsLzlpjqTprNJvhQpRvtRJJQrtQtJ
        llZlzZZzPZZqsTZscHczfnSwSPSwwgSBwwSnPwnf
        chMtcPPgQtthqgvczhMTcCSBLBlGpsFnBnnsGvLplSFG
        bhRmJWDRmHSmFGBnGBps
        jdDZWbrDdDbbdbDrRRWwRjZRVqztCzqtcThcTQtgqVMMVzjq
        flNmNHgcZwTzRLzMLRPlzz
        nntqBJtFbbCbBVCnBtFjJjhVhLzLvRLvgRRvPvpMpvpp
        CgJWjJQDjgBtnGNGfcssNfcwNW
        MrMpMrGBznjPMGCmCrrjdwndfJLQNfdLQNdNggdL
        sJVcZqvhZtVqhDFFsDJslcdwgwvwQwwQNbbTbwfLLTgL
        RDDRcsSsstJstVDDqctszzmRmGjpjBRHPmmGHGrj
        tdplZtlrBGwTlLQQ
        sfsPPvNhWLQBhGQG
        zPVfzVbbMcscvVfzzNgcJHnJZgtrrndJqjJqndrL
        nglLjRCCHLLCnNCLHQnFNQmmVMbVmwMwlMwMMMWwBTsT
        cqtfcqZpzhSvvBfWwbvrbT
        PPqpDSqcSJPdPhtPtqZcpPtGjFRFFFNLJFGRgjFbGRNbHn
        gjtRSLMqLdSgLMCltTSDQcQQqhDcfcfrWDhWrr
        GwFZCwNzFJsPmFFmZmPPNhvDDfDWWmpvQWWfVDQppW
        swNGZbPBGwnCgBBlBljl
        BsrDsnQGwFFQQtfNTBNSffBgBt
        VJlhWVLlRppLQZTCbtZTttgJ
        ppLqqRhQdRPhqPVhdPjhljqHFnGnzFrjFFDznHFHrmwwnH
        CJMmmJLmlshCCdmzjHjPWztgdnjttt
        GwZvGwrgTcFpzHWjnT
        GbqrvrRwbrbGbwwZBbgfhmJMmsDJhNRfLChhCh
        CfgfjCLCgfgFgBhBsccswQwtsQHvBBtc
        SbSMGbnmDMGJWmRmDmvzwtcscWtQzsrPsvHc
        DbJdNpJSnMSJmpSSNVqgqgTTFVQTFqdZLq
        MBMCmlllPSSlmmPPjCMpPgggJcnZgntJsDvHsDZt
        hrNzhrRNbrhbGRbfpVLRGNqqngvnttngJctgDZJGcZvHvZnc
        LTTzVqppSmwdTmQW
        mmlBQmLbsbmRnFnwlqqprF
        dZScZSZSdHcNMDcJwLqRfppzpzfTpHfF
        JLWJLJJJdMmGtgCWjQjt
        PPMzpVDblwGVMMzDLLjrcrjdzjdTzLjd
        RRcCJRcNQRBqtCFBRJJsZWBWrjLWLHZZWndBLWjh
        qQQtttNqsqqtJRgqQfcpcgDcbggplGbfMp
        QmmSTQPmLSmjpczMJtwPzg
        BHHHdwdvDpllvctjZv
        HdrDHNfrrBDGGBhBNfHNsLFqmbRQSwqmGTLnTbSF
        gcMmgRQPqqPPsgjFSvctCHvHllSSHcvd
        JTWfZwhTwzbWwTFTrZnTrDDlDSVtVHLVShtvSHvlHL
        bWbWBfzTfwrWJNbTrnzfTwJFpmMQgqsFRsQGqRMggPmPBG
        GqCWpGGLpmpWjbSDGjGGmwCzZlvMBTrCvsrlwwswCl
        FPFHFVdJgQHJZnslrgvsTrwMlNgw
        hQVchcdcZZpZqcDG
        JbBRgBsRffgPPFQttQvQQMvG
        dmNZgmZVtGTNtNGC
        ZqqndLZnccqRbsrgpggsBc
        DDvMVmTjwFWPBBTzBF
        cqnggcbNNCqbQQqbZbpfQpqgRWlFhLRBhRzRPLFJhlJBfPLh
        dpscpcncbbqcpMVSvSrPDMsrjr
        hGCGZmVRRcMVsGMtmZWssmFLzbFblnnzfmqbfnzNNb
        wjrSPBJdSjjDrggpSJpdrSnlFNlzLTnqNLqqpbqqfMln
        dMwPBHPPJHDdvrBBhshWCGcsQRcHRZGV
        vdHwhqdtLdVnHBZbFBFzbBPS
        TmNCLNDpWfCNmpCgTWNTDMMZlzSggBMzlZlMBlBbZZ
        NQfWcscDNQrhqvGLrdhVjh
        lZLqzzqvgrCRcQcCLD
        HSVVwNTJzwVNTDQrRrrdrBwdhd
        TpNTzsfSTVsSpHVppzFpgvvlqZWZPMvMjPPGGsgP
        BCMLshLdLDDCgwFRwHHqqRqRWd
        QnSqQlGSfpQzTQJNTPNwNPFRFcccHc
        mfJJmztnQpGpvnSzGrsqgrhrBBhqjBrthL
        BSlmzmlvdNnlQlQQJnJHRVFVFVVqMtqRMfSfCw
        WBPsDPBBjfssFHMRRq
        DLWpGhbPjbhZrhZDnBQgdNZmQNgmvdzg
        WWvgBFgHWChBzgBFbjbtPtnPrsHlsRMrwRrMRR
        SGfNpfdGfVpVSGGppSDdwRwclMlfPMwccsntPqPw
        TVdpQDSpQZJpVpDQQFBbthvmWzmgvhbjzJ
        VVCCbzqdbzhFHvbdhZFPmhCPSNRNGSrPJfTNRSGJfGwPST
        LngtBnlcnDvLcTTRfTTwRtGNTG
        DpnBjMpLlLDQWDgvpLvbqzmbjzVjVHqbFFqbFq
        SbzMbNQQSDdmvqqzdSlWFpwZnvpFWWllpFww
        CjLPTPjjLCPtBCLJjBLPLBTFsFFgfwwpZgplpgFnWWRl
        nPncrBHGnmrbdmdmNN
        FnlblGlTTbNVLVtRvQQvgqRQBCvgNr
        DPMDMpMHmnzjPqDhQWvvQvhghq
        zMMcddznsjFTldVGlFGT
        cLSNGLhmRRVmlVCq
        HvzbQBzBMQMpQDpCSlSVZRSCqV
        QwWznWnTbQSMMJQHnvwbWjrhNhLFgsGNNrFLNnFNhd
        dBrWNQWWcTNqqnNN
        bPlmgRgRghlCVlbhwZccCZjZqvmqvmTTvGqmJTvqnGTGvLLJ
        DCDZhjllcpDMrSQS
        ddtNNTFTwRzGRGCwqnBMjlqMHMfqnB
        hDpPsQLLSprhnHVhqhVfHM
        QWLWDQZpgpWbQgfspGGRdcGcCcCcztTGZC
        GGHFdGwFlswcFtnvTfjMjBFfNBjNBZ
        JWmSJLPSRprWWPWVMMVQpZfBvvfQtj
        RzPSPDbDDhbhPDLRhCgGHHccsqGCqqtHzG
        dbSdptWddDMNtdFvttFclqMTZJlJTlMZqJTJTqjC
        BzfwRzrwPzfzLNGmZZCZBTGBqqlH
        hVNVQPNQQQVLPwhRrQwgWWvFdDsFWSbdWgdFSFDb
        hhSnmhtZSFSqZBJSSqqmJJRHPPLgHtPcGGGcWGtvvHwgvG
        fCMpfTQjTrzrzCQMsQdHGHvPGPwLppPRvWPLLc
        MdTzCsCMzNzDCTjlmNhRmRnZBllRVh
        RrFglccgBVVvFNvCvWlgmDbbDfQDtCdjjDbDwmZD
        STnMqSLHJhHHnqLqtnBndbBdfQQZDtZD
        GHJPTBsTSsMMSpSBHJFNlWzcvFlzsVzvzgsv
        lplNdrVrVrWMMVcJfcDDzbqCCpDL
        SSSgvBRSjggPgzvTTRHTvFnfJLbcLsCDLnCLDCcJBsJq
        GmHjjRwvHSjHTRjrlZrNMzVMhVrmVW
        gdtFtgStSbHCbHMPZrFLPLrVlrVZrP
        hQnjMGfDqTvzvpBjVVjPRLRRjJ
        QmsQmhvvMtssHtWw
        RNjTGSCLJCGdRqMRFvMrfzMvzz
        ZpcWcVDpWBmWQMZZpZDpwBcznrshtntvfvhfFtzmvnzvhf
        WHHHcVWgQVCbCllbgMLN
        ZjjdJHSdSzvcZFMhhhDqDHtthw
        rNTlNqVWTmRPlshsDPDlph
        WbTNGNmQBRQbRNQgNGmCLdvvjzcCSBqLcLnScL
        bZwpSpBvSHCBqNzpdFffqQft
        nWRnGRnVnljmlDnzdPfQfdcQPWWfNq
        dmRDGMMlDmnVjgMlhBSwCbCgwHbBHhvv
        NwqLgLBLgnwNNBGpgsQsddhhpQQg
        JcztcZnzVtZvnVcJMTvTJtWtppsQHGdQhhHHQsPhWdPS
        fJTJnMmvZvMvRDqFblNBNNjlBf
        drZVzZzzNWWzwwTWTZrjWcLCqnRqNnLNLqCqnsPPRL
        JhlBgvHBBLnwMBqDwC
        GmGFSHmhJSGJwgFJmwJhJhgQVWVdbSWZQzZTrWtZzjzjTz
        wPGRPpnzgwwGgLddFBFrnrnJdc
        jCsVclQWmCTrJJddrdFs
        lWjlCqfmlWccpGPPSgcf
        hCThCzTdPcPhzqTzMfVfHrhMMmhVHgVM
        lJSJNqwJsZBSsSBFsMprDmFmFDfDDHgHDf
        JNGQsSSNGsbZZZSBwZLPtdLjttnqPCbtPbjC
        vnlWNpbrNrpShhQDLRLB
        MzCjPgffVTVgCJSRQhBdRdJS
        VPHcfcBfTzVMTttMzMfgzMfHvrllWvlnvNvlmGwWNwwNmw
        BwwsqPJqwBssLlFqLRCDzWwzDGRGGWfSRG
        vTtmmthvpphpnNgNvvpvRrDCddDQrCQCzCDrCfnf
        pppccNpTVVlqssHHVzBH
        HWHphZWVWvMZNvpMtfJZgssffsjJgBlslJ
        RLmrFFnGFrFFrrFCRwCrLNPwqfjSJjqJSJBbsqjbbsblfq
        LnFLPPGLrGNRQPmndLzPmPmpcDcMHhcMhVHvczcpHHchcV
        zwqqvNDVggwqVfNQRlszFBsCCJFtFlFPsz
        MSrrGTZPGSSMSjPbTmtlHBBFrFHFsHlsJsct
        MnmMPMSZZGSZWmSjnWgfqdgVQDvnqvRDggDV
        SQCSBShsQnSsSJswsNpVppPPMVpGpnDVgg
        WWjHvmtWZrwvtzzjTTRPrRRrMVNVVGgVGpGR
        lTvWjWLfWwbJCQqBSlbB
        cjPChhswrNVtMZJjVM
        pfvTFvTzLBFndGTlJmVJZmNlCMGtCJ
        nfvFTfpbBFdSFpTLswsWDbchwHCWHrbw
        lNdNPLJJLHHHlpPJcvtVcsBBrrBvBqrVrC
        wDTbwTQRZTMWsVWtmWhhTr
        nzRMbSZtMQDnpzzJHLHNflHP
        HrwwmwcRbmwcbrrTbwwcrTJWLlPshllhLccqLhnnlljhZhjZ
        GMFMSNSpCBSFSdGpNFpBznLlzzhzshlGhhqPhGGL
        nFFSCCSSfttBdddDQNDBQpSSrbrmWJwrHfJHWJrbVwWHrgVr
        SdddNNCmpNNDhMswhsmbhvHM
        frtzqqqFjgrWfgfqtthsnvRHZRRvFlhnvRZb
        rtrgqzzrbWtqLGLLtBWzfGcTNCCVGpSNDppTJJVddNpPSG
        WWJvJvBgpHSHScQRQSVQLzqL
        ddZTlZGZVfQhZRLLMqsR
        rPfwrGGrFjjNTGNCCVBggDJHmNDvbmmpmNJJ
        bbGrJPRVPtfsVfFlMjBV
        WQzhQQQNZQCWNnQDhzWdNjFZggmlHjjmMmMFjFHpMs
        CzQCSWDTWhNhzWhTGJwtRRqTblwcclvP
        HLDvZgZldDTnLLsswMpVLn
        FNVQzQSPznCMmpBwCF
        SqfJPfttqffjJPVlhvhZZtvdVDRZ
        jVsLvHvvdrSjpJFsGzmnmltnml
        nTNTRCTBTmmmFPMJ
        CQnCggWQDgBrrSqHjDDfSS
        ZpNlrZNcmctZbcZlmcmZhhpPvPHvwBMHJPMTMHBTFJvJ
        zmdCnGzGRnLDjQnzPvMFVHMVMLTVwMJv
        GGjqssqgzCnCzQsshcffmrbNrrNZtW
        DNpTwhpLlWMDWNMhbJjGttJFHgDcjtjG
        wqQrdCdqbFtCtJtJ
        vffdrwfPrsmqVBBWRVlRRlTSTWSTlR
        ZqTCTQQTFvsDSsBDvWBd
        hfBLzRLtHHLDDWRRWWDNbd
        pHhhnPzLfJcJhzHLzZjcmwCTqTQgwBqqwg
        WJHgqgFqrVrqgqCHwsJHHVFZzppZFGGfTtpcfbdpzzpd
        RvNMQlMBhwMdMfcpbM
        LLRQNBDSSNSwmDDBQRBRBCHsgrgHLVnJVqLsJsnCPJ
        BFhGsDsDsBtsPGtQDrrMdbdrffrffbJbRt
        cVVqScVSWWvVWgVZjnrHJgLfdrLrnrLLLQ
        WmvqNZzzzZSvVzqvcccSzSmqFGCDTGBPQGDhwCDhNDCwPBQp
        RqTlHHTTrQqHlTqsrVDqHbrZFZwhpBhphZBFhZpDpLLLfB
        nSzGCGdvzdGNPBQQBfhLZfFwFN
        WPPPCJMtJSQMJQCCWMJslRrrRgrMRbRqVqqTRR
        BMtfLsLZfTPmCtGWZrZqJNJqvpZdWr
        bRwgHhhRhbbSRbjSglcgwHHJWPcJdPrnNWrnqnWVVqpdnq
        bgjlSgDljHhjgwMPCLPFDMFPGGBC
        zJWjczcWjSWghZgzgSSSZflTqwlfqTTbnQwhdTnMdl
        NrGVCmNpHFPsrJFbFQMJbJdQTn
        JvrtpHHmrCGJCJmNvNpVCsHVgzWgWDDcjjgjDRStWWDLSgzz
        HzdFsBBVsfnTfsPmPtDcZqtMhDDz
        wrjjRQLlwwwrJQLQbCrbwlJDSlcSDtPZmPSDclWDtcDqWh
        RwgprLbNLrLbCCpRCrJLRLFfsGTNNZHBZnnBvvfffnvd
        MlqqlWZclnPtZtDSSvwQQjgQpNQSRM
        rLJTsBrsJBhshTNNwSQBWNvNgNSg
        JbbbChCHsJzHzbWdGHThlFnnPqlPlGPPGncPtFlD
        WcMVvwNNvjRcwTQwVcpNRcspPCFtbPztbCTFmtPtCJtbCzmz
        grrgDhrnDLnLrdfdLZlLZhmCqzlCbtJlSSStFmttqsJJ
        GHHDdgnLDDhrrrgZrZgLNVVVVcRNvcwjWvpWGcRs
        qhGhPSJtGhGtJtvNjnJjnvmNQQmj
        sRBFlbZsrdBRRGbVGBDwDMDQwQwMNDjjjVNV
        CzCflffbBszdBCbdbrtLcfhhgHLGgPccLSPh
        zShhHFzgJWFVFFHFHhRPNjwqPLPtLbtrbwVjjr
        ssnvTmvCDfpCZTnsfCqwNLNPwbJqNJPwrjZw
        vDvpmcnmnBDnsnJTJmQWMHMWzScFggRFRFSW
        nnVHHPLrnpssLnrpLRnHtHrjJcCdzCjcDzMzdqwRdjdDcJ
        WWTGQQzSGWlTmBbDJJjwMJjcvvlDjw
        TGTWBTWmTbgzghZhgzBgpVNrZPPfntfNrVLNNnZP
        TqhQnqqLnnqddttNqQWdtqQmppSSFFClRmzmFZFLSmSlFF
        BcHjGclVPPBrVrcjrGGDrMgcmmRJbRCFzpZmSDRpRZJJmRzz
        ccGjMgvPvsHMgvBHWlhQdqwtllwNdThs
        WjddwRGgHRRdMbrwHRwWjHDtDZplslnJnZrsDvCprJPJ
        QSLLFqQBffCFststlFnn
        CSSmSqzmVVjWMdMjVWgT
        lTfQRhVpRzjThpRQTTTlvHrvBvHnPMHgHqHJvn
        cGDctCwCdDCGSFcJsFJsFBvgnMBrHvvrqngHgmgssg
        SCbSDSFScNpfbRVVJf
        RrwmdwMVjMjMTghDWNTJDpWfWG
        SbPvNbvbSsPbSvZsPJtJWhHpGGGgJWgJ
        lSFvsLNcqzqLrwnFQMnVdmnn
        FgCJFTWntWTFtPLmJmmQJmCMMpljWZBwlGMljjjlwvwBvZ
        SDSbVbdScSDzbLZMBrjlZpVrZp
        ccsSDhSDffzbLNscfcfDcgqhPgTntqmnQmCgtgQCTJ
        VnCnrHnPPrCwHmVWtqfMQQqzCqffCZ
        DDbDcJJJbpJDGppFpqGZRWfGfddzMWtfWM
        tTTglDcgFjwNPHPPwHlH
        bMGbqqgPqqVVMGnbVqSMmRfPcJmCTPDDLJDTCmDm
        FFjjZvFRsFCctmtvtJWD
        wwFhHjQjwQhZrFjQbngglGbRMnSzgbRH
        GPTTJSgTPrPPmcTPpdJsGGGjqbRvqlztqlRqMzGjRv
        LwnfWLNwwHHQwHnjbbMMjWttqtMmMj
        mhwfBDhnQTpJBcBJps
        HQQHwMfwlltzMlVljQhVjjHPPPFGPFcCGprPTPPfDrDcGf
        pRLdvRvJgqLRBSJCcvFnCDTPTcTGnT
        LBLSJbRSLSqbSdBdgJRRqRbwjHHblQttlwtwhzpjMlwhpw
        NWLNSNSDtgSgghgdcwccmwGntwclnT
        FRCQzJRsvfVVjvzFJfQnffwCcmdwmHmmHmTwmCmdGBcq
        sjfJvjfzPRPzvPPVFMssvSLhSSWrMZnSDNrDDhSLZZ
        FvLpSLtCfPCWhRSZZMZJSW
        jbbjwbHjQmHjHsQrQFMnwTnJznwRzhJRnNTM
        gVrjqGqjgrgsFGLDtDBLLfLB
        cgTvRWWLVScRWflNJJDfVJmVlG
        nPPnnmqjmZHCHBHFdfwNsDhzzfJznhsfhw
        bddmQqQjpdFCQWtLQMMSvMMQRS
        wjnmPwCgjPnRlwnmvmvvPnTwbSSLLvsLDWdbbWzvsLzWbzbz
        NqrGqFHqJlfhhJGbszdWQzzLNtQDzz
        FfHFpphrJqJrpGBffcTnBjCnVTMjMRCnVljT
        SrfSJGJpSgMprMHdhBGhsdsshdGsmm
        nRTRPvQllQlblwvCjTwLTnvBqdhmHDPVsmDmdqshDVhNsP
        lbRFHvRwlnlLbnbjLbLjLCzggSpWfMFzSZpzZFJJWpJr
        vNLlFldlvPtHFPHQRt
        jcpRsScDgshzjqzfVStntBTPMTnmWttntMpp
        fSssgVjDsbqSVbCJClLRJLCZRZZb
        wnHmCJccDDcrNnrNMRDtTzpTlMpTzpBp
        PjSPPGjWjLzTjjMtzzMj
        hWvLLFWvHvczVcVn
        jgtngnnhMthcnLjMgCZvChDsmdNCvNNZDN
        bWqFPbFbLzRFfZBNDNNPZsNd
        RbJzGpzVLLLWHHQgTMwcTptQ
        sJBhsMWQnhhrFBsFhlQQMfrDCDpLlVCddjTdDDpqDLTLdj
        tZHHSRmNHcgmNzpDPJtttqjLqdpL
        HbNbZmcHQJbsFWvs
        VgPNWGbgSjGjfhRRFfzThtmtzF
        qLCQJBqqcPPmLHhHFz
        CcJvplQswNgZlNPSbS
    """.trimIndent()

    private val sampleInput = """
        vJrwpWtwJgWrhcsFMMfFFhFp
        jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
        PmmdzqPrVvPwwTWBwg
        wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
        ttgJtRGJQctTZtZT
        CrZsJsPPZsGzwwsLwLmpwMDw
    """.trimIndent()
}
